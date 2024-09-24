import {Component, EventEmitter, inject, Input, Output, SimpleChanges, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorIntl, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule, Sort} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { PageEvent } from '@angular/material/paginator';
import { PhoneMaskPipe } from '../../pipe/phone-mask.pipe';
import { EditBtnComponent } from '../edit-btn/edit-btn.component';
import { DeleteBtnComponent } from '../delete-btn/delete-btn.component';
import { DisableEmployeeBtnComponent } from '../disable-employee-btn/disable-employee-btn.component';


@Component({
  selector: 'app-table-employee-component',
  standalone: true,
  imports: [MatFormFieldModule,DisableEmployeeBtnComponent,MatIconModule, MatInputModule, MatTableModule, MatSortModule, MatPaginatorModule,PhoneMaskPipe,EditBtnComponent,DeleteBtnComponent],
  templateUrl: './table-employee.component.html',
  styleUrl: './table-employee.component.css'
})
export class TableEmployeeComponent {

  @Input({ required: true }) displayedColumns!: string[];
  @Input({ required: true }) pageSizeOptions!: number[];
  @Input({ required: true }) totalElements!: number;
  @Input({ required: true }) pageSize!: number;
  @Input({ required: true }) dataSource!: MatTableDataSource<any>;
  @Input({ required: true }) currentPage!: number;
  @Input({ required: true }) callbackDelete!: (id:number) => void;
  @Input({ required: true }) callbackDisable!: (id:number) => void;

  @Output() pageChange = new EventEmitter<PageEvent>();
  @Output() sortChange = new EventEmitter<Sort>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(public _MatPaginatorIntl: MatPaginatorIntl) { }



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
}



