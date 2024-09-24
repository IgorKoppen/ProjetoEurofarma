import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { PermissionService } from '../../services/permission.service';
import { RoleService } from '../../services/role.service';
import { DepartmentService } from '../../services/department.service';
import { Permission } from '../../interfaces/permissionInterface';
import { Role } from '../../interfaces/roleInterface';
import { Department } from '../../interfaces/departmentInterface';

@Component({
  selector: 'app-employee-search-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent,MatFormFieldModule,MatInputModule,FormsModule,ReactiveFormsModule,MatSelectModule],
  templateUrl: './employee-search-dialog.component.html',
  styleUrl: './employee-search-dialog.component.css'
})
export class EmployeeSearchDialogComponent {
  searchForm: FormGroup;
  permissions: Permission[] = [];
  roles: Role[] = [];
  departments: Department[] = [];

  constructor(
    private fb: FormBuilder,
    private permissionService: PermissionService,
    private roleService: RoleService,
    private departmentService: DepartmentService
  ) {
    this.searchForm = this.fb.group({
      name: [''],
      surname: [''],
      employeeRegistration: [''],
      enabled: [''],
      roleId: [''],
      permissionId: [''],
      departmentId: ['']
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