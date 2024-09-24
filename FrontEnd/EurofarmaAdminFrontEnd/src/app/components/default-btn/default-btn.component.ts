import { Component, Input, Output } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { EventEmitter } from 'stream';

@Component({
  selector: 'app-default-btn',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './default-btn.component.html',
  styleUrl: './default-btn.component.css'
})
export class DefaultBtnComponent {
  @Input({ required: true }) iconName = ""
  @Input({ required: true }) text = ""
  @Input({ required: true }) callbackSearchDialog!: () => void;
 
  
}
