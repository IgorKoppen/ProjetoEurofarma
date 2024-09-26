import { Component } from '@angular/core';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { EmployeeUpdate } from '../../interfaces/employeeInterface';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PermissionService } from '../../services/permission.service';
import { DepartmentService } from '../../services/department.service';
import { MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { EmployeeService } from '../../services/employee.service';
import { Permission } from '../../interfaces/permissionInterface';
import { RoleInfo } from '../../interfaces/roleInterface';
import { Department } from '../../interfaces/departmentInterface';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-employeeupdate-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent,MatFormFieldModule,MatInputModule,FormsModule,ReactiveFormsModule,MatSelectModule],
  templateUrl: './employeeupdate-dialog.component.html',
  styleUrl: './employeeupdate-dialog.component.css'
})
export class EmployeeupdateDialogComponent {
  updateEmployeeForm!: FormGroup;
  permissions: Permission[] = [];
  roles: RoleInfo[] = [];
  departments: Department[] = [];


  constructor(
    private fb: FormBuilder,
    private permissionService: PermissionService,
    private departmentService: DepartmentService,
    private employeeService: EmployeeService,  
    public dialogRef: MatDialogRef<EmployeeupdateDialogComponent>,
    private dialog: MatDialog 
  ) {}


}
