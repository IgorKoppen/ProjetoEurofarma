import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorIntl, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule, Sort} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { PageEvent } from '@angular/material/paginator';
import { DatePipe } from '@angular/common';
import { TextColorPipe } from '../../pipe/text-color.pipe';
import { PresenceListDialogComponent } from '../presence-list-dialog/presence-list-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { DefaultBtnComponent } from '../default-btn/default-btn.component';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-table-trainings',
  standalone: true,
  imports: [MatFormFieldModule,MatButtonModule,MatIconModule,MatProgressSpinnerModule,DefaultBtnComponent, MatInputModule, MatTableModule, MatSortModule, MatPaginatorModule,TextColorPipe,DatePipe],
  templateUrl: './table-trainings.component.html',
  styleUrl: './table-trainings.component.css'
})
export class TableTrainingsComponent {

  @Input({ required: true }) displayedColumns!: string[];
  @Input({ required: true }) pageSizeOptions!: number[];
  @Input({ required: true }) totalElements!: number;
  @Input({ required: true }) pageSize!: number;
  @Input({ required: true }) dataSource!: MatTableDataSource<any>;
  @Input({ required: true }) currentPage!: number;
  @Input({ required: true }) isLoading!: boolean;
  @Input() employeeRegistrationParamenter: number | undefined = undefined;
  @Output() pageChange = new EventEmitter<PageEvent>();
  @Output() sortChange = new EventEmitter<Sort>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(public _MatPaginatorIntl: MatPaginatorIntl, private dialog: MatDialog ) { }



  ngAfterViewInit() {
    this._MatPaginatorIntl.itemsPerPageLabel = 'Itens por página';
    this._MatPaginatorIntl.firstPageLabel = 'Primeira página';
    this._MatPaginatorIntl.lastPageLabel = 'Última página';
    this._MatPaginatorIntl.nextPageLabel = 'Próxima';
    this._MatPaginatorIntl.previousPageLabel = 'Anterior';

    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;  
      this.emitPageChangeEvent();
    });

    this.paginator.page.subscribe(event => {
      if (event.pageSize !== this.pageSize) {
        this.paginator.pageIndex = 0; 
        this.pageSize = event.pageSize;
      }
      this.emitPageChangeEvent(event);
    });
  
  }

  openListOfPresence = (title: string, trainingId: number): void => {
    this.dialog.open(PresenceListDialogComponent, {
      data: { title, trainingId, filterByEmployeeRegistration: this.employeeRegistrationParamenter},
      width: '1200px',
      height: '750px'
    });
  }

  emitPageChangeEvent(event?: PageEvent) {
    const pageEvent: PageEvent = event || {
      pageIndex: this.paginator.pageIndex,
      pageSize: this.paginator.pageSize,
      length: this.totalElements
    };
    this.pageChange.emit(pageEvent);
  }
 
  onPageChange(event: PageEvent) {
    this.pageChange.emit(event);
  }

  onSortChanged(event: Sort){
    this.sortChange.emit(event);
  }

  convertStringToDate(dateString: string): Date {
    const cleanDateString = dateString.split(',')[0];
    const [datePart, timePart] = cleanDateString.split(' ');
    const [day, month, year] = datePart.split('/');
    const isoString = `${year}-${month}-${day}T${timePart}`;
    return new Date(isoString);
  }
}

