import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { PermissionService } from '../../services/permission.service';
import { RoleService } from '../../services/role.service';
import { DepartmentService } from '../../services/department.service';
import { Permission } from '../../interfaces/permissionInterface';
import { Role } from '../../interfaces/roleInterface';
import { Department } from '../../interfaces/departmentInterface';
import { MatRadioModule } from '@angular/material/radio';

@Component({
  selector: 'app-employee-search-dialog',
  standalone: true,
  imports: [MatButtonModule,MatRadioModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent,MatFormFieldModule,MatInputModule,FormsModule,ReactiveFormsModule,MatSelectModule],
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
      filterType: ['criteria'],
      name: [null],
      surname: [null],
      employeeRegistration: [null],
      enabled: [null],
      roleId: [null],
      permissionId: [null],
      departmentId: [null]
    });
  }
ngOnInit() {
  this.loadPermissions();
  this.loadRoles();
  this.loadDepartments();
  const filterTypeControl = this.searchForm.get('filterType');
  if (filterTypeControl) {
    filterTypeControl.valueChanges.subscribe(() => {
      this.resetFormFields();
    });
  }
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
private resetFormFields() {
  const filterType = this.searchForm.get('filterType')?.value;
  const resetFields = {
      name: null,
      tagId: null,
      departmentId: null,
      employeeRegistration: null,
      instructorRegistration: null
  };

  if (filterType === 'employeeRegistration') {
      resetFields.instructorRegistration = null;
  } 
   else{
    resetFields.departmentId = null;
    resetFields.name = null;
    resetFields.tagId = null;
  }

  this.searchForm.patchValue(resetFields, { emitEvent: false });
}

getFormClass() {
  return this.searchForm.get('filterType')?.value !== 'criteria' ? 'formMini' : 'form';
}
}