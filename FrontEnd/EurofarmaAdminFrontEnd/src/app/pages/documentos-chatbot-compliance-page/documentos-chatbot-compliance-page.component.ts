import { Component } from '@angular/core';
import { TableComponent } from '../../components/table-component/table.component';
import { MatTableDataSource } from '@angular/material/table';
import { EuroComplianceChatbotDocsService } from '../../services/euro-compliance-chatbot-docs.service';
import { Document } from '../../interfaces/documentinterfaces';
import { MatIcon } from '@angular/material/icon';


@Component({
  selector: 'app-documentos-chatbot-compliance-page',
  standalone: true,
  imports: [TableComponent,MatIcon],
  templateUrl: './documentos-chatbot-compliance-page.component.html',
  styleUrl: './documentos-chatbot-compliance-page.component.css'
})
export class DocumentosChatbotCompliancePageComponent {
  displayedColumns: string[] = ['id', 'metadata'];
  dataSource: MatTableDataSource<any>;
  documentData:Document[] = []
  constructor(private complianceService: EuroComplianceChatbotDocsService) {
    complianceService.findAllDocs().subscribe(document => {this.documentData = document.documents});
    this.dataSource = new MatTableDataSource(this.documentData);
    console.log(this.documentData)
   }
 
}
