import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Permission } from '../../interfaces/permissionInterface';
import {RoleInfo } from '../../interfaces/roleInterface';
import { Department } from '../../interfaces/departmentInterface';
import { PermissionService } from '../../services/permission.service';
import { RoleService } from '../../services/role.service';
import { DepartmentService } from '../../services/department.service';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-employeeinsert-dialog',
  standalone: true,
  imports: [MatButtonModule,MatRadioModule,NgClass, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent,MatFormFieldModule,MatInputModule,FormsModule,ReactiveFormsModule,MatSelectModule],
  templateUrl: './employeeinsert-dialog.component.html',
  styleUrl: './employeeinsert-dialog.component.css'
})
export class EmployeeinsertDialogComponent {
  insertTypeForm!: FormGroup;
  singleInsertForm!: FormGroup;
  bulkInsertForm!: FormGroup;
  insertFile!: File;
  fileErrorMessage: string = '';
  permissions: Permission[] = [];
  roles: RoleInfo[] = [];
  departments: Department[] = [];
  selectedFileName: string = '';

  constructor(
    private fb: FormBuilder,
    private permissionService: PermissionService,
    private departmentService: DepartmentService,
    public dialogRef: MatDialogRef<EmployeeinsertDialogComponent>
  ) {}

  ngOnInit() {
    this.loadPermissions();
    this.loadDepartments();

    this.insertTypeForm = this.fb.group({
      insertType: ['insert'],
    });

    this.singleInsertForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      surname: ['', [Validators.required, Validators.minLength(4)]],
      departmentId: [null, Validators.required],
      roleId: [null, Validators.required],
      permissionId: [[], []],
      employeeRegistration: [
        null,
        [Validators.required, Validators.min(1), Validators.max(99999), Validators.pattern('^[0-9]*$')],
      ],
      cellphoneNumber: [null, [Validators.required, Validators.pattern('^\\+[0-9]*$')]],
    });

    this.bulkInsertForm = this.fb.group({
      file: [null, Validators.required],
    });
  }

  onDepartmentSelectionChange(event: any): void {
    const departmentId = event.value;
    this.loadRoles(departmentId);
  }

  private loadPermissions() {
    this.permissionService.findAll().subscribe((data) => {
      this.permissions = data;
    });
  }

  private loadRoles(departmentId: number): void {
    const department = this.departments.find((dept) => dept.id === departmentId);
    if (department) {
      this.roles = department.roles;
    } else {
      this.roles = [];
    }
  }

  private loadDepartments() {
    this.departmentService.findAll().subscribe((data) => {
      this.departments = data;
    });
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {

      this.insertFile = file;
      this.selectedFileName = file.name;
      if (!this.insertFile.name.endsWith('.xlsx')) {
        this.fileErrorMessage = 'Formato de arquivo inválido. Certifique-se de que você está enviando um arquivo .xlsx';
      } else {
        this.fileErrorMessage = '';
      }
    } else {
      this.selectedFileName = '';
    }
  }

  triggerFileSelection() {
    const fileInput = document.getElementById('file') as HTMLInputElement;
    if (fileInput) {
      fileInput.click();
    }
  }

  getSingleInsertPayload() {
    return { insertType: 'insert', ...this.singleInsertForm.value };
  }

confirmDialog() {
    if (this.insertTypeForm.value.insertType === 'bulkInsertWithExcel') {
      this.dialogRef.close({ insertType: 'bulkInsertWithExcel', file: this.insertFile });
    } else {
      this.dialogRef.close(this.getSingleInsertPayload());
    }
  }
}