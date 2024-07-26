import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-add-document-component',
  standalone: true,
  imports: [],
  templateUrl: './add-document-component.component.html',
  styleUrl: './add-document-component.component.css'
})
export class AddDocumentComponentComponent {
 @Input() titulo = "";
}
