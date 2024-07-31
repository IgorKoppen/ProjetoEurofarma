import { Component } from '@angular/core';
import { AddDocumentComponentComponent } from '../../components/add-document-component/add-document.component';
import { EuroDataChatbotDocsService } from '../../services/euro-data-chatbot-docs.service';

@Component({
  selector: 'app-add-documento-eurofarma-page',
  standalone: true,
  imports: [AddDocumentComponentComponent],
  templateUrl: './add-document-eurofarma-page.component.html',
  styleUrl: './add-document-eurofarma-page.component.css'
})
export class AddDocumentoEurofarmaPageComponent {

 
   constructor(private euroDataService:EuroDataChatbotDocsService){}

  onSubmit= (file:File): void => {
  if (file) {
    const metadata = { title: file.name};
    this.euroDataService.addNewDoc(file, metadata).subscribe({
        next: () => {
         console.log("Enviado")
        },
        error: (error: any) => {
          if (error.status === 404) {
            console.error('Document not found or already deleted.');
          } else if (error.status === 403) {
            console.error('Authorization error. Check your API key and permissions.');
          } else {
            console.error('An error occurred:', error);
          }
        },
   });
  }
}
}
