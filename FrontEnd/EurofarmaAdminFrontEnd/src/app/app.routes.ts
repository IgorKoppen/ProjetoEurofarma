import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { AdminLayoutPageComponent } from './pages/admin-layout-page/admin-layout-page.component';
import { AddDocumentoCompliancePageComponent } from './pages/add-documento-compliance-page/add-documento-compliance-page.component';
import { AddDocumentoEurofarmaPageComponent } from './pages/add-documento-eurofarma-page/add-documento-eurofarma-page.component';

export const routes: Routes = [
    {path: '', component: LoginPageComponent},
    {path: 'dashboard', component: AdminLayoutPageComponent , children: [
        {path: 'complianceAdicionar',component: AddDocumentoCompliancePageComponent},
        {path: 'eurodataAdicionar',component: AddDocumentoEurofarmaPageComponent}
    ]}
];
