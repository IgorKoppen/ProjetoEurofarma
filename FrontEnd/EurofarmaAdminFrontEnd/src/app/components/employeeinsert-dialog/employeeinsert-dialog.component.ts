import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Permission } from '../../interfaces/permissionInterface';
import { Role } from '../../interfaces/roleInterface';
import { Department } from '../../interfaces/departmentInterface';
import { PermissionService } from '../../services/permission.service';
import { RoleService } from '../../services/role.service';
import { DepartmentService } from '../../services/department.service';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle } from '@angular/material/dialog';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-employeeinsert-dialog',
  standalone: true,
  imports: [MatButtonModule,MatRadioModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent,MatFormFieldModule,MatInputModule,FormsModule,ReactiveFormsModule,MatSelectModule],
  templateUrl: './employeeinsert-dialog.component.html',
  styleUrl: './employeeinsert-dialog.component.css'
})
export class EmployeeinsertDialogComponent {
  insertForm: FormGroup;
  insertFile!: File;
  permissions: Permission[] = [];
  roles: Role[] = [];
  departments: Department[] = [];

  constructor(
    private fb: FormBuilder,
    private permissionService: PermissionService,
    private roleService: RoleService,
    private departmentService: DepartmentService
  ) {
    this.insertForm = this.fb.group({
      employeeRegistration: [
        null,
        [
          Validators.required, 
          Validators.min(1),
          Validators.max(99999), 
          Validators.pattern('^[0-9]*$')
        ]
      ],
      name: [
        null,
        [
          Validators.required, 
          Validators.minLength(4) 
        ]
      ],
      surname: [
        null,
        [
          Validators.required, 
          Validators.minLength(4) 
        ]
      ],
      cellphoneNumber: [
        null,
        [
          Validators.required,
          Validators.pattern('^\\+[0-9]*$')
        ]
      ],
      roleId: [
        null,
        [
          Validators.required 
        ]
      ],
      permissionsIds: [null] 
    });
  }
 
  ngOnInit() {
    this.loadPermissions();
    this.loadRoles();
    this.loadDepartments();
  }
  
  private loadPermissions() {
    this.permissionService.findAll().subscribe(data => {
      this.permissions = data;
    });
  }
  
  private loadRoles() {
    this.roleService.findAll().subscribe(data => {
      this.roles = data;
    });
  }
  
  private loadDepartments() {
    this.departmentService.findAll().subscribe(data => {
      this.departments = data;
    });
  }
}
