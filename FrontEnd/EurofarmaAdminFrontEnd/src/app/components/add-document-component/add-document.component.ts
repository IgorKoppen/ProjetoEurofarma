import { Component, Input } from '@angular/core';
import { EuroDataChatbotDocsService } from '../../services/euro-data-chatbot-docs.service';

@Component({
  selector: 'app-add-document-component',
  standalone: true,
  imports: [],
  templateUrl: './add-document.component.html',
  styleUrl: './add-document.component.css'
})
export class AddDocumentComponentComponent {
  @Input() titulo = "";
  @Input({required:true}) onSubmit: (file: File) => void = () => {};
  selectedFile: File | null = null;
  fileErrorMessage: string | null = null;

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const maxSizeInMB = 10;
    const maxSizeInBytes = maxSizeInMB * 1024 * 1024;

    if (file) {
      if (file.size > maxSizeInBytes) {
        this.fileErrorMessage = `O tamanho do arquivo excede o limite de  ${maxSizeInMB}MB.`;
        this.selectedFile = null;
        (event.target as HTMLInputElement).value = '';
      } else {
        this.selectedFile = file;
        this.fileErrorMessage = null;
      }
    }
  }
 
  sendForm(event:MouseEvent){
    event.preventDefault()
    if(this.selectedFile){
    this.onSubmit(this.selectedFile);
    }
  }
}