import { Component, inject } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { DocumentChatbot } from '../../interfaces/documentInterfaces';
import { TableDocumentComponent } from '../../components/table-document/table-document.component';
import { EuroDataChatbotDocsService } from '../../services/euro-data-chatbot-docs.service';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatIcon } from '@angular/material/icon';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-documentos-chatbot-euro-data-page',
  standalone: true,
  imports: [TableDocumentComponent,MatIcon,RouterLink],
  templateUrl: './documentos-chatbot-euro-data-page.component.html',
  styleUrl: './documentos-chatbot-euro-data-page.component.css'
})
export class DocumentosChatbotEuroDataPageComponent {
  displayedColumns: string[] = ['id', 'Título','Data de criação','actions'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource();
  documentData: DocumentChatbot[] = [];
  isLoading: boolean = true;
  
  constructor(private euroDataService: EuroDataChatbotDocsService) {}
  
  ngOnInit(): void {
    this.loadDocuments();
  }
 
  loadDocuments = (): void => {
    this.isLoading = true;
    this.euroDataService.findAllDocs().subscribe({
      next: (document) => {
        this.documentData = document.documents;
        this.dataSource.data = this.documentData;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Failed to load documents:', error);
        this.isLoading = false;
      }
    });
  }

  refreshDocuments = (): void => {
    this.loadDocuments();
  }


  deleteDoc = (id: string): void => {
    this.euroDataService.deleteDoc(id).subscribe({
      next: () => {
        this.documentData = this.documentData.filter(doc => doc.id !== id);
        this.dataSource.data = this.documentData;
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