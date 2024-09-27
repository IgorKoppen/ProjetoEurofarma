import { Component, Inject } from '@angular/core';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { Employee, EmployeeUpdate } from '../../interfaces/employeeInterface';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PermissionService } from '../../services/permission.service';
import { DepartmentService } from '../../services/department.service';
import { MAT_DIALOG_DATA, MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
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
export class EmployeeUpdateDialogComponent  {
  updateEmployeeForm!: FormGroup;
  permissions: Permission[] = [];
  roles: RoleInfo[] = [];
  department!: Department;

  constructor(
    private fb: FormBuilder,
    private permissionService: PermissionService,
    private departmentService: DepartmentService,
    private employeeService: EmployeeService,
    private dialog:MatDialog,
    public dialogRef: MatDialogRef<EmployeeUpdateDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public employeeData: Employee
  ) {}

  ngOnInit(): void {
    this.loadPermissions();

    this.updateEmployeeForm = this.fb.group({
      name: [this.employeeData.name, Validators.required],
      surname: [this.employeeData.surname, Validators.required],
      cellphoneNumber: [this.employeeData.cellphone_number, Validators.required],
      roleId: [this.employeeData.role.id, Validators.required],
      permissionsIds: [this.employeeData.permission.map(p => p.id), Validators.required]
    });

    this.loadDepartmentRoles(this.employeeData.role.department.id!).then(() => {
      this.updateEmployeeForm = this.fb.group({
        name: [this.employeeData.name, Validators.required],
        surname: [this.employeeData.surname, Validators.required],
        cellphoneNumber: [this.employeeData.cellphone_number, Validators.required],
        roleId: [this.employeeData.role.id, Validators.required],
        permissionsIds: [this.employeeData.permission.map(p => p.id), Validators.required]
      });
    });
  }

  loadPermissions(): void {
    this.permissionService.findAll().subscribe((data: Permission[]) => {
      this.permissions = data;
    });
  }

  loadDepartmentRoles(departmentId: number): Promise<void> {
    return new Promise((resolve, reject) => {
      this.departmentService.findById(departmentId).subscribe(
        (department: Department) => {
          this.department = department;
          this.roles = department.roles;
          resolve();
        },
        (error) => reject(error)
      );
    });
  }

  updateEmployee(): void {
    const updatedEmployee: EmployeeUpdate = {
      name: this.updateEmployeeForm.value.name,
      surname: this.updateEmployeeForm.value.surname,
      cellphoneNumber: this.updateEmployeeForm.value.cellphoneNumber,
      roleId: this.updateEmployeeForm.value.roleId,
      permissionsIds: this.updateEmployeeForm.value.permissionsIds
    };
    this.employeeService.update(this.employeeData.id!, updatedEmployee).subscribe(
      () => {
        this.dialogRef.close(true);
      },
      (error) => {
        this.openDialogError("Erro ao atualiar funcionario", error.error.message, '200ms', '100ms');
        console.error('Erro ao atualizar o funcionário', error);
      }
    );
  }
  openDialogError(title: string, description: string, enterDuration: string, exitDuration: string) {
    this.dialog.open(ErrorDialogComponent, {
      width: "500px",
      data: { title, description },
      enterAnimationDuration: enterDuration,
      exitAnimationDuration: exitDuration
    });
  }
}