import {Component, Input, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorIntl, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';




@Component({
  selector: 'app-table-component',
  standalone: true,
  imports: [MatFormFieldModule,MatIconModule, MatInputModule, MatTableModule, MatSortModule, MatPaginatorModule],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css'
})
export class TableComponent {
  @Input({required: true}) displayedColumns: string[] = [];
  @Input({required: true}) dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();
  clickedRow: any = null;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(public _MatPaginatorIntl: MatPaginatorIntl) {
    this.dataSource = new MatTableDataSource();
  }
  ngOnInit() {
    this._MatPaginatorIntl.itemsPerPageLabel = 'Items por páginas';
    this._MatPaginatorIntl.firstPageLabel = 'Primeira página';
    this._MatPaginatorIntl.lastPageLabel = 'Última página';
    this._MatPaginatorIntl.nextPageLabel = 'Próxima';
    this._MatPaginatorIntl.previousPageLabel = 'Anterior'; 
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  onRowClicked(row: any) {
    this.clickedRow = row;
  }
}

