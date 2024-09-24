import { Component, ViewChild } from '@angular/core';
import {  TrainingSearchParams } from '../../interfaces/SearchParamsIntefaces';
import { MatTableDataSource } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { DefaultBtnComponent } from '../../components/default-btn/default-btn.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { TrainingService } from '../../services/training.service';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { Sort } from '@angular/material/sort';
import { catchError, map, merge, of, startWith, switchMap } from 'rxjs';
import { TableTrainingsComponent } from '../../components/table-trainings/table-trainings.component';
import { TrainingPaginationResponse } from '../../interfaces/trainingInterface';
import { TrainingSearchDialogComponent } from '../../components/training-search-dialog/training-search-dialog.component';

@Component({
  selector: 'app-training-list-page',
  standalone: true,
  imports: [MatProgressSpinnerModule,DefaultBtnComponent,MatIconModule,MatButtonModule, MatDividerModule,TableTrainingsComponent],
  templateUrl: './training-list-page.component.html',
  styleUrl: './training-list-page.component.css'
})
export class TrainingListPageComponent {
  trainings: any[] = [];
  totalElements: number = 0;
  currentPage: number = 0;
  pageSize: number = 10;
  dataSource = new MatTableDataSource<any>();
  isLoadingResults = true;
  sortDirection = 'asc';
  sortField = 'id';
  searchParams: TrainingSearchParams | null = null;

  displayedColumns: string[] = [
    'id', 'name', 'code', 'description', 'tags', 'departments', 'opened', 'creation_date', 'closing_date', 'actions'
  ];



  constructor(private trainingService: TrainingService, private dialog: MatDialog) {}

  @ViewChild(TableTrainingsComponent) tableComponent!: TableTrainingsComponent;


  ngAfterViewInit() {
    const sort = this.tableComponent.sort;
    const paginator = this.tableComponent.paginator;

    if (sort && paginator) {
      merge(sort.sortChange, paginator.page)
        .pipe(
          startWith({}),
          switchMap(() => {
            if (this.searchParams) {
              return this.trainingService.search({...this.searchParams, page: this.currentPage, size: this.pageSize, sort: `${this.sortField},${this.sortDirection}`});
            } else {
              this.isLoadingResults = true;
              return this.trainingService.findAllPagination(this.currentPage, this.pageSize, `${this.sortField},${this.sortDirection}`);
        
            }
          }),
          map(response => {
            this.isLoadingResults = false;
            this.totalElements = response.page.totalElements;
            return response._embedded.trainingDTOList;
          }),
          catchError(error => {
            this.isLoadingResults = false;
            return of([]);
          })
        ).subscribe(data => {
          this.dataSource.data = data;
        });
    }
  }

  openSearchDialog = (): void => {
    const dialogRef = this.dialog.open(TrainingSearchDialogComponent, {
      width: '650px',
      enterAnimationDuration: '300ms',
      exitAnimationDuration: '300ms'
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
       this.searchTrainings(result)
      }
    });
  }

  loadTrainings() {
    this.isLoadingResults = true;
    this.trainingService.findAllPagination(this.currentPage, this.pageSize, `${this.sortField},${this.sortDirection}`).pipe(
      map(response => {
        this.trainings = response._embedded.trainingDTOList;
        this.totalElements = response.page.totalElements;
        this.dataSource.data = this.trainings;
        this.isLoadingResults = false;
        this.tableComponent.paginator.length = this.totalElements;
      }),
      catchError(error => {
        this.isLoadingResults = false;
        return of([]);
      })
    ).subscribe();
  }


 
  cleanSearch(){
    this.searchParams = null;
    this.currentPage = 0;
    this.loadTrainings();
  }



  searchTrainings(params: TrainingSearchParams): void {
    this.searchParams = params;
    this.isLoadingResults = true;
    this.trainingService.search({
      ...params,
      page: this.currentPage,
      size: this.pageSize,
      sort: `${this.sortField},${this.sortDirection}`
    }).pipe(
      map(response => this.handleResponse(response)),
      catchError(error => {
        this.isLoadingResults = false;
        return of([]);
      })
    ).subscribe(data => {
      this.dataSource.data = data;
      this.tableComponent.paginator.length = this.totalElements;
    });
  }

  handleResponse(response: TrainingPaginationResponse) {
    this.isLoadingResults = false;
    this.totalElements = response.page.totalElements;
    if (response._embedded && response._embedded.trainingDTOList) {
      this.trainings = response._embedded.trainingDTOList;
    } else {
      this.trainings = [];
    }
    return this.trainings;
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;

    if (this.searchParams) {
      this.searchTrainings({...this.searchParams, page: this.currentPage, size: this.pageSize});
    } else {
      this.loadTrainings();
    }
  }

  onSortChanged(event: Sort ) {
    this.sortField = event.active;
    this.sortDirection = event.direction;

    if (this.searchParams) {
      this.searchTrainings({...this.searchParams, sort: `${this.sortField},${this.sortDirection}`});
    } else {
      this.loadTrainings();
    }
  }
}
