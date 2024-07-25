import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { AdminLayoutPageComponent } from './pages/admin-layout-page/admin-layout-page.component';

export const routes: Routes = [
    {path: '', component: LoginPageComponent},
    {path: 'dashboard', component: AdminLayoutPageComponent , children: []}
];
