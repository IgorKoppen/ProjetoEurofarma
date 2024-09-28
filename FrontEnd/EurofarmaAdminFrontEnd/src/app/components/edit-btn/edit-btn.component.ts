import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Employee } from '../../interfaces/employeeInterface';

@Component({
  selector: 'app-edit-btn',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  templateUrl: './edit-btn.component.html',
  styleUrl: './edit-btn.component.css'
})
export class EditBtnComponent {
  @Input({ required: true }) callback!: () => void;
}
