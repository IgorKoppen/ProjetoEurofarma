import { Component } from '@angular/core';
import { TableComponent} from '../../components/table-component/table.component';

@Component({
  selector: 'app-employee-page',
  standalone: true,
  imports: [TableComponent],
  templateUrl: './employee-page.component.html',
  styleUrl: './employee-page.component.css'
})
export class EmployeePageComponent {

}
