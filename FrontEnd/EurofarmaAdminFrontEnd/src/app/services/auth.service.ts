import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenResponse } from '../interfaces/TokenResponseInterface';
import { UserCredential } from '../interfaces/userCredential';
import { catchError, map, Observable, throwError } from 'rxjs';
import { AuthException } from '../Errors/AuthError';
import { environment } from '../../environments/environment.development';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {
  }
  private baseUrl = `${environment.backEndApiBase.url}/auth`

  private headers = new HttpHeaders({
    'Accept': 'application/json',

  });

  signin(userCredential: UserCredential): Observable<boolean> {
    return this.http.post<TokenResponse>(this.baseUrl + '/signin', userCredential, { headers: this.headers }).pipe(
      map((res: TokenResponse) => {
        if (res.authenticated) {
          sessionStorage.setItem('user', JSON.stringify(res));
          const expirationDate = new Date(res.expiration);
          document.cookie = `employeeRegistration=${res.employeeRegistration}; expires=${expirationDate.toUTCString()}; path=/; secure; SameSite=Strict;`
          document.cookie = `accessToken=${res.accessToken}; expires=${expirationDate.toUTCString()}; path=/; secure; SameSite=Strict;`;
          document.cookie = `refreshToken=${res.refreshToken}; expires=${expirationDate.toUTCString()}; path=/; secure; SameSite=Strict;`;

          return true;
        } else {
          throw new AuthException('Authentication failed');
        }
      }),
      catchError((error) => {
        const errorMessage = error.error?.message || 'An error occurred during authentication';
        return throwError(() => new AuthException(errorMessage));
      })
    );
  }

  refreshToken(){
    const employeeRegistration = this.getCookie('employeeRegistration');
    const refreshToken = this.getCookie('refreshToken');
  }

  logout(){
    sessionStorage.removeItem('user');
    this.deleteCookie('accessToken');
    this.deleteCookie('refreshToken');
  }

  authenticated(): boolean {
    const accessToken = this.getCookie('accessToken');
    return !!accessToken;
  }

  private getCookie(name: string): string | null {
    const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
    return match ? match[2] : null;
  }

  private deleteCookie(name: string): void {
    document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/; secure; SameSite=Strict;`;
  }
}
