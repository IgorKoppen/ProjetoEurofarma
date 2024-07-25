import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SidebarAdminComponent } from '../../components/sidebar-admin/sidebar-admin.component';


@Component({
  selector: 'app-admin-layout-page',
  standalone: true,
  imports: [RouterModule,SidebarAdminComponent],
  templateUrl: './admin-layout-page.component.html',
  styleUrl: './admin-layout-page.component.css'
})
export class AdminLayoutPageComponent {

}
