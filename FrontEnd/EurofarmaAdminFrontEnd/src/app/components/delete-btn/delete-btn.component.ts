import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';


@Component({
  selector: 'app-delete-btn',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  templateUrl: './delete-btn.component.html',
  styleUrl: './delete-btn.component.css'
})
export class DeleteBtnComponent {
  @Input({required: true}) deleteId!: number | string;
  @Input({required:true}) dialogTitle!: string;
  @Input({required:true})  dialogDescription!: string;
  @Output() deleteCallBack = new EventEmitter<number | string>();

   constructor(private dialog: MatDialog){}
 
  openDialog( enterAnimationDuration: string, exitAnimationDuration: string): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: { 
        deleteId: this.deleteId,
        title: this.dialogTitle ,
        description: this.dialogDescription,
        confirmText: "Deletar"},
        enterAnimationDuration,
        exitAnimationDuration,
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteCallBack.emit(this.deleteId);
      }
    });
  }
}
