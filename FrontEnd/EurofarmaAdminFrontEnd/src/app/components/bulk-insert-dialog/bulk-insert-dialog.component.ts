import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { EmployeeRegistrationResponse } from '../../interfaces/employeeInsertInMassInterface';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-bulk-insert-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent],
  templateUrl: './bulk-insert-dialog.component.html',
  styleUrl: './bulk-insert-dialog.component.css'
})
export class BulkInsertDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<BulkInsertDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: EmployeeRegistrationResponse
  ) {}

  onClose(): void {
    this.dialogRef.close();
  }
}
