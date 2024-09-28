import { Component } from '@angular/core';
import { Department } from '../../interfaces/departmentInterface';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { DepartmentService } from '../../services/department.service';
import { RoleService } from '../../services/role.service';
import { MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { RoleInsert } from '../../interfaces/roleInterface';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';

@Component({
  selector: 'app-insert-role-dialog',
  standalone: true,
  imports: [
    MatButtonModule, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle,
    MatFormFieldModule, MatInputModule, FormsModule, ReactiveFormsModule, MatSelectModule
  ],
  templateUrl: './insert-role-dialog.component.html',
  styleUrls: ['./insert-role-dialog.component.css']
})
export class InsertRoleDialogComponent {
  insertRoleForm!: FormGroup;
  departments: Department[] = [];

  constructor(
    private fb: FormBuilder,
    private departmentService: DepartmentService,
    private roleService: RoleService,
    public dialogRef: MatDialogRef<InsertRoleDialogComponent>,
    private dialog: MatDialog
  ) {
    this.initializeForm();
  }

  ngOnInit() {
    this.loadDepartments();
  }

  private initializeForm(): void {
    this.insertRoleForm = this.fb.group({
      name: ['', Validators.required],
      departmentId: ['', Validators.required]
    });
  }

  private loadDepartments(): void {
    this.departmentService.findAll().subscribe({
      next: (data: Department[]) => {
        this.departments = data;
      },
      error: (error) => {
        console.error('Erro ao carregar departamentos', error.error.message);
      }
    });
  }

  insertRole(): void {
    const roleInsert: RoleInsert = this.insertRoleForm.value;
    this.roleService.insert(roleInsert).subscribe({
      next: (role) => {
        this.dialogRef.close(role);
      },
      error: (error: any) => {
        this.openDialogError("Erro ao inserir cargo", error.error.message, '200ms', '100ms');
      }
    });
  }

  openDialogError(title: string, description: string, enterDuration: string, exitDuration: string): void {
    this.dialog.open(ErrorDialogComponent, {
      width: "500px",
      data: { title, description },
      enterAnimationDuration: enterDuration,
      exitAnimationDuration: exitDuration
    });
  }
}