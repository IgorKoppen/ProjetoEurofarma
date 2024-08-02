import { Component } from '@angular/core';
import { AddDocumentComponentComponent } from '../../components/add-document-component/add-document.component';
import { EuroComplianceChatbotDocsService } from '../../services/euro-compliance-chatbot-docs.service';

@Component({
  selector: 'app-add-documento-compliance-page',
  standalone: true,
  imports: [AddDocumentComponentComponent],
  templateUrl: './add-document-compliance-page.component.html',
  styleUrl: './add-document-compliance-page.component.css'
})
export class AddDocumentoCompliancePageComponent {
  constructor(private euroComplianceService:EuroComplianceChatbotDocsService){}
  


  onSubmit = (file:File): void => {
  if (file) {
    const metadata = { title: file.name};
    this.euroComplianceService.addNewDoc(file, file.name).subscribe({
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
