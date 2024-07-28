import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { AdminLayoutPageComponent } from './pages/admin-layout-page/admin-layout-page.component';
import { AddDocumentoCompliancePageComponent } from './pages/add-document-compliance-page/add-document-compliance-page.component';
import { AddDocumentoEurofarmaPageComponent } from './pages/add-document-eurofarma-page/add-document-eurofarma-page.component';
import { EmployeePageComponent } from './pages/employee-page/employee-page.component';

export const routes: Routes = [
    {path: '', component: LoginPageComponent},
    {path: 'dashboard', component: AdminLayoutPageComponent , children: [
        {path: 'complianceAdicionar',component: AddDocumentoCompliancePageComponent},
        {path: 'eurodataAdicionar',component: AddDocumentoEurofarmaPageComponent},
        {path: 'funcionarios', component: EmployeePageComponent}
    ]}
];
