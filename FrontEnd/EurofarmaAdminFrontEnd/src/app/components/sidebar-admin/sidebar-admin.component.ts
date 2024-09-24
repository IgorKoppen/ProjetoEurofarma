import { Component } from '@angular/core';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-sidebar-admin',
  standalone: true,
  imports: [MatSidenavModule,MatMenuModule,MatIconModule,RouterLink],
  templateUrl: './sidebar-admin.component.html',
  styleUrl: './sidebar-admin.component.css'
})
export class SidebarAdminComponent {


  constructor(private authService: AuthService, private router: Router){}

  isOpen:boolean = false;
   minimizeOrOpen = () => {
    this.isOpen = !this.isOpen;
  }
  logout(){
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
