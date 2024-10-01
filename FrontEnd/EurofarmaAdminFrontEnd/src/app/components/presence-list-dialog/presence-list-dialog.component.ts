import { Component, Inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { TrainingService } from '../../services/training.service';
import { Attendance } from '../../interfaces/presenceList';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { PresenceListComponent } from '../presence-list/presence-list.component';
import { MatTableDataSource } from '@angular/material/table';
import { ShortInstructorInfo } from '../../interfaces/instructorInfoInterface';
import { Training } from '../../interfaces/trainingInterface';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import { MatButton } from '@angular/material/button';

@Component({
  selector: 'app-presence-list-dialog',
  standalone: true,
  imports: [MatDialogTitle, MatButton, MatDialogContent, PresenceListComponent],
  templateUrl: './presence-list-dialog.component.html',
  styleUrl: './presence-list-dialog.component.css',
})
export class PresenceListDialogComponent {
  public loading: boolean = true;
  public dataSourceAttendanceList!: MatTableDataSource<Attendance>;
  public dataSourceTrainers!: MatTableDataSource<ShortInstructorInfo>;
  constructor(
    public dialogRef: MatDialogRef<PresenceListDialogComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: {
      title: String;
      trainingId: number;
      trainingData: Training;
      filterByEmployeeRegistration: number | undefined;
    },
    private trainingService: TrainingService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getPresenceList(this.data.trainingId);
  }

  getPresenceList(trainingId: number): void {
    this.trainingService.findTrainingDetailsById(trainingId).subscribe({
      next: (response) => {
        this.dataSourceAttendanceList = new MatTableDataSource<Attendance>(
          response.attendanceList
        );
        this.dataSourceTrainers = new MatTableDataSource<ShortInstructorInfo>(
          response.instructors
        );
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.openErrorDialog(
          'Erro ao carregar a lista de presença',
          'Não foi possível carregar a lista de presença. Tente novamente mais tarde.'
        );
      },
    });
  }

  openErrorDialog(title: string, description: string): void {
    this.dialog.open(ErrorDialogComponent, {
      data: { title, description },
      width: '400px',
    });
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  generatePDF() {
    const doc = new jsPDF();
    doc.setLanguage("pt-BR");
    const margin = 14;
    let yPosition = 20;

    const image = new Image();
    image.src = 'assets/image/EurofarmaLogo600x312.png';
    const imgWidth = 80;
    const imgHeight = 25;
    doc.addImage(image, 'png', margin, yPosition, imgWidth, imgHeight);
    yPosition += imgHeight + 10;

    doc.setFontSize(18);
    doc.text('Informações do treinamento', margin, yPosition);
    doc.setFontSize(12);
    yPosition += 10;

    const trainingData = [
      { label: 'Nome', value: this.data.trainingData.name },
      { label: 'Descrição', value: this.data.trainingData.description },
      {
        label: 'Tem quiz',
        value: this.data.trainingData.hasQuiz ? 'Sim' : 'Não',
      },
      {
        label: 'Status',
        value: this.data.trainingData.opened ? 'Aberto' : 'Fechado',
      },
      { label: 'Data de criação', value: this.data.trainingData.creation_date },
      {
        label: 'Data de fechamento',
        value: this.data.trainingData.closing_date,
      },
    ];

    trainingData.forEach((item) => {
      doc.text(`${item.label}: ${item.value}`, margin, yPosition);
      yPosition += 10;
    });

    if (this.data.trainingData.hasQuiz && this.data.trainingData.quiz) {
      doc.setFontSize(14);
      yPosition += 10;
      doc.text('Quiz', margin, yPosition);
      yPosition += 10;
      doc.setFontSize(12);
      const quizData = [
        { label: 'Nome', value: this.data.trainingData.quiz.nome },
        { label: 'Descrição', value: this.data.trainingData.quiz.description },
        { label: 'Nota Mínima', value: this.data.trainingData.quiz.notaMinima },
        { label: 'Número de Questões', value: this.data.trainingData.quiz.questionsNumber }
      ];

      quizData.forEach((item) => {
        doc.text(`${item.label}: ${item.value}`, margin, yPosition);
        yPosition += 10;
      });
    }


    doc.addPage();
    yPosition = 20;
    doc.setFontSize(20);
    doc.text('Treinadores', margin, yPosition);
    yPosition += 10;

    const trainersColumns = ['Nome', 'Sobrenome', 'RE'];
    const trainersRows = this.dataSourceTrainers.data.map((row) => [
      row.name,
      row.surname,
      row.employeeRegistration,
    ]);

    (doc as any).autoTable({
      head: [trainersColumns],
      body: trainersRows,
      startY: yPosition,
      theme: 'grid',
      headStyles: { cellWidth: 'auto', fillColor: [0, 72, 142] },
    });

    doc.addPage();
    yPosition = 20;
    doc.setFontSize(20);
    doc.text('Lista de Presença', margin, yPosition);
    yPosition += 10;

    let attendanceColumns = [
      'Nome',
      'Sobrenome',
      'RE',
      'Assinatura',
      'Data de registro',
    ];

    if (this.data.trainingData.hasQuiz) {
      attendanceColumns = [
        'Nome',
        'Sobrenome',
        'RE',
        'Assinatura',
        'Data de\nregistro',
        'Tentativas\nQuiz',
        'Nota',
      ];
    }

   
    attendanceColumns.push('status');

    const attendanceRows = this.dataSourceAttendanceList.data.map((row) => {
      const baseRow = [
        row.name,
        row.surname,
        row.employeeRegistration,
        row.signature ? '' : 'Assinatura',
        row.registrationDate,
      ];

      if (this.data.trainingData.hasQuiz) {
        const quizTries = row.quizTries != null ? row.quizTries : 0;
        const nota = row.nota != null ? row.nota : 0;
     
        return [...baseRow, quizTries, nota, 'Aprovado'];
      }


      return [...baseRow, 'Aprovado'];
    });

    (doc as any).autoTable({
      head: [attendanceColumns],
      body: attendanceRows,
      startY: yPosition,
      theme: 'grid',
      headStyles: { cellWidth: 'auto', fillColor: [0, 72, 142] },
      styles: { cellPadding: 3 },
      columnStyles: {
        3: { minCellHeight: 20 },
        4: { cellWidth: 25 },
        5: { cellWidth: 25 },
        6: { cellWidth: 15 },
      },
      didDrawCell: (data: any) => {
        if (
          data.column.index === 3 &&
          data.section != 'head' &&
          this.dataSourceAttendanceList.data[data.row.index].signature
        ) {
          const signature =
            this.dataSourceAttendanceList.data[data.row.index].signature;
          const imgWidth = 60;
          const imgHeight = 20;
          const cellWidth = data.cell.width;
          const cellHeight = data.cell.height;
          const widthRatio = imgWidth / cellWidth;
          const heightRatio = imgHeight / cellHeight;
          const ratio = Math.max(widthRatio, heightRatio);
          const imgScaledWidth = imgWidth / ratio / 1.1;
          const imgScaledHeight = imgHeight / ratio / 1.1;
          const imgXPos = data.cell.x + (data.cell.width - imgScaledWidth) / 2;
          const imgYPos =
            data.cell.y + (data.cell.height - imgScaledHeight) / 2;
          doc.addImage(
            signature,
            'PNG',
            imgXPos,
            imgYPos,
            imgScaledWidth,
            imgScaledHeight
          );
        }
      },
    });
    doc.save(this.data.trainingData.name + '_Treinamento.pdf');
  }
}
