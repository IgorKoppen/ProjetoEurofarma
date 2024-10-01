import { Component } from '@angular/core';
import { AddDocumentComponentComponent } from '../../components/add-document-component/add-document.component';
import { EuroDataChatbotDocsService } from '../../services/euro-data-chatbot-docs.service';
import { Router } from '@angular/router';
import { ErrorDialogComponent } from '../../components/error-dialog/error-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-add-documento-eurofarma-page',
  standalone: true,
  imports: [AddDocumentComponentComponent],
  templateUrl: './add-document-eurofarma-page.component.html',
  styleUrl: './add-document-eurofarma-page.component.css'
})
export class AddDocumentoEurofarmaPageComponent {

 
   constructor(private euroDataService:EuroDataChatbotDocsService,private router: Router,private dialog:MatDialog){}

  onSubmit= (file:File,linkBack:String): void => {
  if (file) {
    const metadata = { title: file.name};
    this.euroDataService.addNewDoc(file, file.name).subscribe({
        next: () => {
         this.router.navigate([linkBack]);
        },
        error: (error: any) => {
          if (error.status === 404) {
            this.openDialogError("Documento já deletado ou em processo", error.error.message , '200ms', '100ms');
            console.error('Document not found or already deleted.');
          } else if (error.status === 403) {
            this.openDialogError("Erro de autorização", error.error.message , '200ms', '100ms');
          } else {
            this.openDialogError("Erro", error.error.message , '200ms', '100ms');
          }
        },
   });
  }
}
 
openDialogError(erroTitle: string, erroDescription: string, enterAnimationDuration: string, exitAnimationDuration: string): void {
  this.dialog.open(ErrorDialogComponent, {
    width: '400px',
    data: { 
      title: erroTitle, 
      description: erroDescription 
    },
    enterAnimationDuration,
    exitAnimationDuration,
  });
}
}
