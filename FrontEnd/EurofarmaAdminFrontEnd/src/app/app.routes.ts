import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { AdminLayoutPageComponent } from './pages/admin-layout-page/admin-layout-page.component';
import { AddDocumentoCompliancePageComponent } from './pages/add-document-compliance-page/add-document-compliance-page.component';
import { AddDocumentoEurofarmaPageComponent } from './pages/add-document-eurofarma-page/add-document-eurofarma-page.component';
import { DocumentosChatbotCompliancePageComponent } from './pages/documentos-chatbot-compliance-page/documentos-chatbot-compliance-page.component';
import { DocumentosChatbotEuroDataPageComponent } from './pages/documentos-chatbot-euro-data-page/documentos-chatbot-euro-data-page.component';
import { AuthGuard } from './guards/auth.guards';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { TrainingListPageComponent } from './pages/training-list-page/training-list-page.component';
import { EmployeeListPageComponent } from './pages/employee-list-page/employee-list-page.component';

export const routes: Routes = [
    {path: '', component: LoginPageComponent},
    {path: 'admin', component: AdminLayoutPageComponent, 
        canActivate: [AuthGuard] ,
         children: [
        {path: 'complianceAdicionar',component: AddDocumentoCompliancePageComponent},
        {path: 'eurodataAdicionar',component: AddDocumentoEurofarmaPageComponent},
        {path: 'euroComplianceDocs',component: DocumentosChatbotCompliancePageComponent},
        {path: 'euroDataDocs',component: DocumentosChatbotEuroDataPageComponent},
        {path: 'funcionarios', component: EmployeeListPageComponent},
        {path: 'treinamentos', component: TrainingListPageComponent}
    ]},
    {path:'**', component: NotFoundPageComponent}
];
