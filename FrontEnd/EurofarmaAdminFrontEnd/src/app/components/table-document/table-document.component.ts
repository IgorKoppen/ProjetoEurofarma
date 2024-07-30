import {Component, Input, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorIntl, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { DocumentChatbot } from '../../interfaces/documentInterfaces';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-table-document',
  standalone: true,
  imports: [MatFormFieldModule,MatIconModule, MatInputModule, MatTableModule, MatSortModule, MatPaginatorModule,DatePipe],
  templateUrl: './table-document.component.html',
  styleUrl: './table-document.component.css'
})
export class TableDocumentComponent {
  @Input({ required: true }) displayedColumns: string[] = [];
  @Input({ required: true }) dataSource: MatTableDataSource<DocumentChatbot> = new MatTableDataSource<DocumentChatbot>();
  @Input() deleteDoc: (id: string) => void = () => {};
  

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(public _MatPaginatorIntl: MatPaginatorIntl) { }

  ngOnInit() {
    this._MatPaginatorIntl.itemsPerPageLabel = 'Items por páginas';
    this._MatPaginatorIntl.firstPageLabel = 'Primeira página';
    this._MatPaginatorIntl.lastPageLabel = 'Última página';
    this._MatPaginatorIntl.nextPageLabel = 'Próxima';
    this._MatPaginatorIntl.previousPageLabel = 'Anterior'; 

    this.dataSource.filterPredicate = (data: DocumentChatbot, filter: string) => {
        const filterValue = filter.toLowerCase();

        const formattedCreationDate = new Date(data.metadata.CreationDate * 1000).toLocaleDateString('pt-BR'); 

        return (
            data.metadata.title.toLowerCase().includes(filterValue) ||
            formattedCreationDate.includes(filterValue) ||  
            data.id.toString().includes(filterValue) 
        );
    };
}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.sort.sortChange.subscribe(() => {
      this.sortData();
    });
  }

  sortData() {
    const data = this.dataSource.data.slice(); 
    const active = this.sort.active; 
    const direction = this.sort.direction; 

    if (active && direction) {
      this.dataSource.data = data.sort((a, b) => {
        const isAsc = direction === 'asc';
        switch (active) {
          case 'metadata.title':
            return this.compare(a.metadata.title, b.metadata.title, isAsc);
          case 'metadata.CreationDate':
            return this.compare(a.metadata.CreationDate, b.metadata.CreationDate, isAsc);
          default:
            return 0;
        }
      });
    } else {
      this.dataSource.data = data; 
    }
  }

  compare(a: any, b: any, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  
  
}
