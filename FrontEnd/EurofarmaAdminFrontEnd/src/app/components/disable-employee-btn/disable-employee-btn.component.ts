import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-disable-employee-btn',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  templateUrl: './disable-employee-btn.component.html',
  styleUrl: './disable-employee-btn.component.css'
})
export class DisableEmployeeBtnComponent {
  @Input({required:true}) isDisabled = false;
  @Input({required: true}) employeeId!: number | string;
  @Input({required: true}) dialogTitle!: string;
  @Output() disableCallback = new EventEmitter<number | string>();

  constructor(private dialog: MatDialog){}

  openDialog(enterAnimationDuration: string, exitAnimationDuration: string): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '300px',
      data: { 
        deleteId: this.employeeId,
        title: this.dialogTitle ,
        description: null,
        confirmText: "Confirmar"},
        enterAnimationDuration,
        exitAnimationDuration,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.disableCallback.emit(this.employeeId);
      }
    });
}
}