import { Component } from '@angular/core';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-sidebar-admin',
  standalone: true,
  imports: [MatSidenavModule,MatMenuModule,MatIconModule,RouterLink],
  templateUrl: './sidebar-admin.component.html',
  styleUrl: './sidebar-admin.component.css'
})
export class SidebarAdminComponent {
  isOpen:boolean = false;
   minimizeOrOpen = () => {
    console.log(this.isOpen)
    this.isOpen = !this.isOpen;
  }
}
