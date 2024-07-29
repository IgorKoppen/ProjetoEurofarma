import { Component } from '@angular/core';
import { TableComponent} from '../../components/table-component/table.component';
import { MatTableDataSource } from '@angular/material/table';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { EmployeeService } from '../../services/employee.service';
import { MatIcon } from '@angular/material/icon';
export interface EmployeeData {
  id: number;
  nome: string;
  sobrenome: string;
  username: string;
  telefone: string;
  status: boolean;
  cargo: string,
  departamento: string,
  permissões: string[]
}



@Component({
  selector: 'app-employee-page',
  standalone: true,
  imports: [TableComponent,MatProgressSpinnerModule,MatIcon],
  templateUrl: './employee-page.component.html',
  styleUrl: './employee-page.component.css'
})

export class EmployeePageComponent {
  displayedColumns: string[] = ['id', 'nome', 'sobrenome','username', 'telefone','status','cargo','departamento','permissões'];
  dataSource: MatTableDataSource<any>;
  constructor(private employeeService: EmployeeService) {
    this.dataSource = new MatTableDataSource(employeeService.getAll());
   }
}
