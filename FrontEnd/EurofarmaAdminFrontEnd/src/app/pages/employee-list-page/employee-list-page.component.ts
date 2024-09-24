import { Component, ViewChild } from '@angular/core';

import { MatTableDataSource } from '@angular/material/table';
import { PageEvent } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { TableEmployeeComponent } from '../../components/table-employee-component/table-employee.component';
import { catchError, map, merge, of, startWith, switchMap, throwError } from 'rxjs';
import { Sort } from '@angular/material/sort';
import { DefaultBtnComponent } from '../../components/default-btn/default-btn.component';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../components/error-dialog/error-dialog.component';
import { DeleteException } from '../../Errors/DeleteError';
import { EmployeeSearchDialogComponent } from '../../components/employee-search-dialog/employee-search-dialog.component';

import { EmployeePaginationResponse } from '../../interfaces/employeePagination';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { EmployeeSearchParams } from '../../interfaces/SearchParamsIntefaces';
import { EmployeeService } from '../../services/employee.service';



@Component({
  selector: 'app-employee-list-page',
  standalone: true,
  imports: [TableEmployeeComponent,MatProgressSpinnerModule,DefaultBtnComponent,MatIconModule,MatButtonModule, MatDividerModule],
  templateUrl: './employee-list-page.component.html',
  styleUrl: './employee-list-page.component.css'
})
export class EmployeeListPageComponent{
 
  employees: any[] = [];
  totalElements: number = 0;
  currentPage: number = 0;
  pageSize: number = 10;
  dataSource = new MatTableDataSource<any>();
  isLoadingResults = true;
  sortDirection = 'asc';
  sortField = 'id';
  searchParams: EmployeeSearchParams | null = null;

  displayedColumns: string[] = [
    'id', 'name', 'surname', 'enabled', 'role', 'department', 'employee_registration', 'cellphone_number',
    'permission', 'actions'
  ];

  @ViewChild(TableEmployeeComponent) tableComponent!: TableEmployeeComponent;

  constructor(private employeeService: EmployeeService, private dialog: MatDialog) {}

  ngAfterViewInit() {
    const sort = this.tableComponent.sort;
    const paginator = this.tableComponent.paginator;

    if (sort && paginator) {
      merge(sort.sortChange, paginator.page)
        .pipe(
          startWith({}),
          switchMap(() => {
            if (this.searchParams) {
              return this.employeeService.search({...this.searchParams, page: this.currentPage, size: this.pageSize, sort: `${this.sortField},${this.sortDirection}`});
            } else {
              this.isLoadingResults = true;
              return this.employeeService.findAllWithPagination(this.currentPage, this.pageSize, `${this.sortField},${this.sortDirection}`);
            }
          }),
          map(response => {
            this.isLoadingResults = false;
            this.totalElements = response.page.totalElements;
            return response._embedded.employeeInfoDTOList;
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

  loadEmployees() {
    this.isLoadingResults = true;
    this.employeeService.findAllWithPagination(this.currentPage, this.pageSize, `${this.sortField},${this.sortDirection}`).pipe(
      map(response => {
        this.employees = response._embedded.employeeInfoDTOList;
        this.totalElements = response.page.totalElements;
        this.dataSource.data = this.employees;
        this.isLoadingResults = false;
        this.tableComponent.paginator.length = this.totalElements;
      }),
      catchError(error => {
        this.isLoadingResults = false;
        return of([]);
      })
    ).subscribe();
  }

  disableEmployee = (id: number): void => {
    this.employeeService.disable(id).subscribe(() => {
      if (this.searchParams) {
        this.searchEmployees({...this.searchParams});
      } else {
        this.loadEmployees();
      }
    });
  }

  deleteEmployee = (id: number): void => {
    this.employeeService.delete(id).pipe(
      catchError(error => {
        this.openDialog("Erro ao deletar o funcionário", "Não é possível excluir este funcionário porque ele está associado a registros de treinamentos.", '200ms', '100ms');
        return throwError(() => new DeleteException("Erro ao deletar o funcionário"));
      })
    ).subscribe(() => {
      if (this.totalElements - 1 <= this.currentPage * this.pageSize && this.currentPage > 0) {
        this.currentPage--;
      }
      if (this.searchParams) {
        this.searchEmployees({...this.searchParams});
      } else {
        this.loadEmployees();
      }
    });
  }
  cleanSearch(){
    this.searchParams = null;
    this.currentPage = 0;
    this.loadEmployees();
  }


  openDialog(erroTitle: string, erroDescription: string, enterAnimationDuration: string, exitAnimationDuration: string): void {
    this.dialog.open(ErrorDialogComponent, {
      width: '300px',
      data: { 
        title: erroTitle, 
        description: erroDescription 
      },
      enterAnimationDuration,
      exitAnimationDuration,
    });
  }

  openSearchDialog = (): void => {
    const dialogRef = this.dialog.open(EmployeeSearchDialogComponent, {
      width: '650px',
      enterAnimationDuration: '300ms',
      exitAnimationDuration: '300ms'
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
       
        this.searchEmployees(result);
      }
    });
  }



  searchEmployees(params: EmployeeSearchParams): void {
    this.searchParams = params;
    this.isLoadingResults = true;
    this.employeeService.search({
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

  handleResponse(response: EmployeePaginationResponse) {
    this.isLoadingResults = false;
    this.totalElements = response.page.totalElements;
    if (response._embedded && response._embedded.employeeInfoDTOList) {
      this.employees = response._embedded.employeeInfoDTOList;
    } else {
      this.employees = [];
    }
    return this.employees;
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;

    if (this.searchParams) {
      this.searchEmployees({...this.searchParams, page: this.currentPage, size: this.pageSize});
    } else {
      this.loadEmployees();
    }
  }

  onSortChanged(event: Sort) {
    this.sortField = event.active;
    this.sortDirection = event.direction;

    if (this.searchParams) {
      this.searchEmployees({...this.searchParams, sort: `${this.sortField},${this.sortDirection}`});
    } else {
      this.loadEmployees();
    }
  }
}