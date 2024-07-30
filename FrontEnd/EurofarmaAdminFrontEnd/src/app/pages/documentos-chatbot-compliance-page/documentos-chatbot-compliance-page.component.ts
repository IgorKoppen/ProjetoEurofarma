import { Component } from '@angular/core';
import { TableComponent } from '../../components/table-component/table.component';
import { MatTableDataSource } from '@angular/material/table';
import { EuroComplianceChatbotDocsService } from '../../services/euro-compliance-chatbot-docs.service';
import { MatIcon } from '@angular/material/icon';
import { DocumentChatbot } from '../../interfaces/documentInterfaces';
import { TableDocumentComponent } from '../../components/table-document/table-document.component';


@Component({
  selector: 'app-documentos-chatbot-compliance-page',
  standalone: true,
  imports: [TableDocumentComponent,MatIcon],
  templateUrl: './documentos-chatbot-compliance-page.component.html',
  styleUrl: './documentos-chatbot-compliance-page.component.css'
})
export class DocumentosChatbotCompliancePageComponent {
  displayedColumns: string[] = ['id', 'Título','Data de criação','actions'];
  dataSource: MatTableDataSource<any>;
  documentData:DocumentChatbot[] = []
  constructor(private complianceService: EuroComplianceChatbotDocsService) {
    complianceService.findAllDocs().subscribe(document => {this.documentData = document.documents});
    this.dataSource = new MatTableDataSource(this.documentData);
   }
 
}
