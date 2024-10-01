import { Component } from '@angular/core';
import { AddDocumentComponentComponent } from '../../components/add-document-component/add-document.component';
import { EuroComplianceChatbotDocsService } from '../../services/euro-compliance-chatbot-docs.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ErrorDialogComponent } from '../../components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-add-documento-compliance-page',
  standalone: true,
  imports: [AddDocumentComponentComponent],
  templateUrl: './add-document-compliance-page.component.html',
  styleUrl: './add-document-compliance-page.component.css'
})
export class AddDocumentoCompliancePageComponent {
  constructor(private euroComplianceService:EuroComplianceChatbotDocsService,private dialog:MatDialog,private router:Router){}
  


  onSubmit= (file:File,linkBack:String): void => {
    if (file) {
      const metadata = { title: file.name};
      this.euroComplianceService.addNewDoc(file, file.name).subscribe({
          next: () => {
           this.router.navigate([linkBack]);
          },
          error: (error: any) => {
            if (error.status === 404) {
              this.openDialogError("Documento já deletado ou em processo", error.error.message , '200ms', '100ms');
            
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
