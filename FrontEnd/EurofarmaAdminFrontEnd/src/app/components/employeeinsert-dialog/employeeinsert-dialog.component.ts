import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Permission } from '../../interfaces/permissionInterface';
import {RoleInfo } from '../../interfaces/roleInterface';
import { Department } from '../../interfaces/departmentInterface';
import { PermissionService } from '../../services/permission.service';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { DepartmentService } from '../../services/department.service';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { NgClass } from '@angular/common';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { EmployeeInsert } from '../../interfaces/employeeInterface';
import { EmployeeService } from '../../services/employee.service';
import { EmployeeRegistrationResponse } from '../../interfaces/employeeInsertInMassInterface';
import { BulkInsertDialogComponent } from '../bulk-insert-dialog/bulk-insert-dialog.component';
import { InsertDepartmentDialogComponent } from '../insert-department-dialog/insert-department-dialog.component';
import { MatIcon } from '@angular/material/icon';
import { InsertRoleDialogComponent } from '../insert-role-dialog/insert-role-dialog.component';

@Component({
  selector: 'app-employeeinsert-dialog',
  standalone: true,
  imports: [MatButtonModule,MatIcon,MatProgressBarModule,MatRadioModule,NgClass, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent,MatFormFieldModule,MatInputModule,FormsModule,ReactiveFormsModule,MatSelectModule],
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
  isLoadingDocument:boolean = false;
  constructor(
    private fb: FormBuilder,
    private permissionService: PermissionService,
    private departmentService: DepartmentService,
    private employeeService: EmployeeService,  
    public dialogRef: MatDialogRef<EmployeeinsertDialogComponent>,
    private dialog: MatDialog 
  ) {}

  ngOnInit() {
    this.loadPermissions();
    this.loadDepartments();

    this.insertTypeForm = this.fb.group({
      insertType: ['insert'],
    });

    this.singleInsertForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      surname: ['', [Validators.required, Validators.minLength(3)]],
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
      this.bulkInsertEmployees(this.insertFile);
    } else {
      const employeeData: EmployeeInsert = {
        name: this.singleInsertForm.value.name,
        surname: this.singleInsertForm.value.surname,
        employeeRegistration: this.singleInsertForm.value.employeeRegistration,
        cellphoneNumber: this.singleInsertForm.value.cellphoneNumber,
        roleId: this.singleInsertForm.value.roleId,
        permissionsIds: this.singleInsertForm.value.permissionId
      };
      this.insertEmployee(employeeData);
    }
  }

  insertEmployee(employee: EmployeeInsert): void {
    this.employeeService.insert(employee).subscribe({
      next: () => {
        this.dialogRef.close(true); 
      },
      error: (error: any) => {
        this.openDialogError(error.error.message, "Ocorreu um problema ao tentar inserir o funcionário.", '200ms', '100ms');
      }
    });
  }

  bulkInsertEmployees(file: File): void {
    this.isLoadingDocument = true;
    this.employeeService.bulkInsertWithExcel(file).subscribe({
      next: (response: EmployeeRegistrationResponse) => {
        this.isLoadingDocument = false;
        this.openDialogShowBulkInsert(response, '200ms', '100ms');
        this.dialogRef.close(true); 
      },
      error: (error : any) => {
        this.isLoadingDocument = false;
        this.openDialogError("Erro ao inserir funcionários em massa", "Ocorreu um problema ao tentar inserir os funcionários em massa. Erro:" + error.error.message, '200ms', '100ms');
      }
    });
  }

  openDialogShowBulkInsert(data: EmployeeRegistrationResponse, enterDuration: string, exitDuration: string) {
    this.dialog.open(BulkInsertDialogComponent, {
      width: "600px",
      data: data,
      enterAnimationDuration: enterDuration,
      exitAnimationDuration: exitDuration
    });
  }

  openDialogError(title: string, description: string, enterDuration: string, exitDuration: string) {
    this.dialog.open(ErrorDialogComponent, {
      width: "500px",
      data: { title, description },
      enterAnimationDuration: enterDuration,
      exitAnimationDuration: exitDuration
    });
  }

  openNewDepartmentDialog(enterDuration: string, exitDuration: string){
    const dialogRef = this.dialog.open(InsertDepartmentDialogComponent, {
      width: "350px",
      enterAnimationDuration: enterDuration,
      exitAnimationDuration: exitDuration
    }
  );
  dialogRef.afterClosed().subscribe((result) => {
    if (result) {
      this.loadDepartments();
    }
  })
  }
  openNewRoleDialog(enterDuration: string, exitDuration: string){
    const dialogRef = this.dialog.open(InsertRoleDialogComponent, {
      width: "350px",
      enterAnimationDuration: enterDuration,
      exitAnimationDuration: exitDuration
    }
  );
  dialogRef.afterClosed().subscribe((result) => {
    if (result) {
      this.loadDepartments();
    }
  })
  }
}