import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { DepartmentService } from '../../services/department.service';
import { MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-insert-department-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle,
    MatFormFieldModule, MatInputModule, FormsModule, ReactiveFormsModule],
  templateUrl: './insert-department-dialog.component.html',
  styleUrl: './insert-department-dialog.component.css',
})
export class InsertDepartmentDialogComponent {
  insertDepartmentForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private departmentService: DepartmentService,
    public dialogRef: MatDialogRef<InsertDepartmentDialogComponent>,
    private dialog: MatDialog
  ) {
    this.initializeForm();
  }

  private initializeForm(): void {
    this.insertDepartmentForm = this.fb.group({
      departName: ['', Validators.required],
    });
  }

  insertDepartment(): void {
    const departmentInsert = this.insertDepartmentForm.value;
    this.departmentService.insert(departmentInsert).subscribe({
      next: (department) => {
        this.dialogRef.close(department);
      },
      error: (error: any) => {
        this.openDialogError(
          'Error inserting department',
          error.message,
          '200ms',
          '100ms'
        );
      },
    });
  }
  openDialogError(
    title: string,
    description: string,
    enterDuration: string,
    exitDuration: string
  ) {
    this.dialog.open(ErrorDialogComponent, {
      width: '500px',
      data: { title, description },
      enterAnimationDuration: enterDuration,
      exitAnimationDuration: exitDuration,
    });
  }
}
