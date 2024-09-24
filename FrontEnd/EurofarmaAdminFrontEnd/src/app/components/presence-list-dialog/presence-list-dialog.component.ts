import { Component, Inject, Input } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { TrainingService } from '../../services/training.service';
import { Attendance } from '../../interfaces/presenceList';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { PresenceListComponent } from '../presence-list/presence-list.component';
import { MatTableDataSource } from '@angular/material/table';
import { ShortInstructorInfo } from '../../interfaces/instructorInfoInterface';

@Component({
  selector: 'app-presence-list-dialog',
  standalone: true,
  imports: [MatDialogTitle, MatDialogContent,PresenceListComponent],
  templateUrl: './presence-list-dialog.component.html',
  styleUrl: './presence-list-dialog.component.css'
})
export class PresenceListDialogComponent {

  public loading: boolean = true; 
  public dataSourceAttendanceList!: MatTableDataSource<Attendance>;
  public dataSourceTrainers!: MatTableDataSource<ShortInstructorInfo>;
  constructor(
    public dialogRef: MatDialogRef<PresenceListDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {title: String, trainingId: number, filterByEmployeeRegistration: number | undefined },
    private trainingService: TrainingService, 
    private dialog: MatDialog 
  ) {}

  ngOnInit(): void {
    this.getPresenceList(this.data.trainingId); 
  }


  getPresenceList(trainingId: number): void {
    this.trainingService.findTrainingDetailsById(trainingId).subscribe({
      next: (response) => {
        this.dataSourceAttendanceList = new MatTableDataSource<Attendance>(response.attendanceList);
        this.dataSourceTrainers = new MatTableDataSource<ShortInstructorInfo>(response.instructors);
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.openErrorDialog('Erro ao carregar a lista de presença', 'Não foi possível carregar a lista de presença. Tente novamente mais tarde.');
      }
    });
  }


  openErrorDialog(title: string, description: string): void {
    this.dialog.open(ErrorDialogComponent, {
      data: { title, description },
      width: '400px'
    });
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

}
