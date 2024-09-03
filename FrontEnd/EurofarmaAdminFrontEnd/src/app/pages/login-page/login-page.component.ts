import { Component, signal } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import { AuthService } from '../../services/auth.service';
import { UserCredential } from '../../interfaces/userCredential';
import { Router } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [MatButtonModule,MatFormFieldModule,MatIconModule,MatInputModule,FormsModule,ReactiveFormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {
  hide = signal(true);
  errorMenssage = "";
  loginForm : FormGroup = new FormGroup({
    userName: new FormControl("",[Validators.required]),
    password: new FormControl("",[Validators.required])
  })

  constructor(private authService: AuthService, private router: Router) {
    if(authService.authenticated()){
      this.router.navigate(['/admin']);
    }
  }

  clickEvent(event: MouseEvent) {
    event.preventDefault();
    this.hide.set(!this.hide());
    event.stopPropagation();
  }
  


  login(event: SubmitEvent) {
    event.preventDefault();
    const userCredential : UserCredential = {
      userName: this.loginForm.value.userName,
      password: this.loginForm.value.password,
    };

    this.authService.signin(userCredential).subscribe({
      next: (isAuthenticated) => {
        if (isAuthenticated) {
          this.router.navigate(['/admin']);
        }
      },
      error: (err) => {
        this.errorMenssage = err
      }
    });
  }

}
