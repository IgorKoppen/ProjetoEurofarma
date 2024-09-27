import { Component, Input, ViewChild } from '@angular/core';
import { Attendance } from '../../interfaces/presenceList';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import {ShortInstructorInfo } from '../../interfaces/instructorInfoInterface';

@Component({
  selector: 'app-presence-list',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule
  ],
  templateUrl: './presence-list.component.html',
  styleUrl: './presence-list.component.css'
})
export class PresenceListComponent {
  @Input({required: true}) instructors!: MatTableDataSource<ShortInstructorInfo>;
  @Input() filterByEmployeeRegistration: number | undefined = undefined;
  @Input({required: true}) hasQuizTraining!: boolean;
  displayedColumnsAttendece: string[] = []
  displayedColumnsInstructors: string[] = ['name', 'surname', 'employeeRegistration'];
  private _dataSource!: MatTableDataSource<Attendance>;



  @Input({required: true})
  set dataSource(value: MatTableDataSource<Attendance>) {
    this._dataSource = value;
    if (this._dataSource) {
      this._dataSource.sort = this.sort;
      this.applyInitialFilter();
    }
  }
  ngOnInit(){
    this.displayedColumnsAttendece = this.hasQuizTraining
    ? ['id', 'name', 'surname', 'employeeRegistration', 'signature', 'registrationDate', 'quizTries', 'nota']
    : ['id', 'name', 'surname', 'employeeRegistration', 'signature', 'registrationDate'];
  }

  get dataSource(): MatTableDataSource<Attendance> {
    return this._dataSource;
  }

  @Input({required:true}) isLoadingResults: boolean = true;

  @ViewChild(MatSort, { static: false }) sort!: MatSort;

  ngAfterViewInit() {
    if (this.dataSource) {
      console.log('Data Source:', this.dataSource.data); // Check the content
  
      this.dataSource.sort = this.sort;
    }

    setTimeout(() => {
      if (this.dataSource) {
        this.dataSource.connect().subscribe(data => {
          this.dataSource.sort = this.sort;
        });
        this.applyInitialFilter();
      }
    });
  }

 
  applyInitialFilter() {
    if (this.filterByEmployeeRegistration !== undefined && this.filterByEmployeeRegistration !== null) {
      this.dataSource.filterPredicate = (data: Attendance, filter: string) => {
        return data.employeeRegistration
          .toString()
          .includes(this.filterByEmployeeRegistration!.toString());
      };
  
      this.dataSource.filter = this.filterByEmployeeRegistration.toString();
    } else {
     
      this.dataSource.filterPredicate = (data: Attendance, filter: string) => true;
      this.dataSource.filter = ''; 
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim();
  }
}