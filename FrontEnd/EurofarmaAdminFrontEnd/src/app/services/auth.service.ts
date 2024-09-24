import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { UserCredential } from '../interfaces/userCredential';
import { catchError, map, Observable, throwError } from 'rxjs';
import { AuthException } from '../Errors/AuthError';
import { environment } from '../../environments/environment.development';
import { deleteCookie, getCookie } from '../util/cookieFunction';
import { TokenResponse } from '../interfaces/tokenResponseInterface';



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
            if (res.authenticated && res.roles && res.roles.includes('admin')) {
                sessionStorage.setItem('user', JSON.stringify(res));
                const expirationDate = new Date(res.expiration);
                document.cookie = `employeeRegistration=${res.employeeRegistration}; expires=${expirationDate.toUTCString()}; path=/; secure; SameSite=Strict;`;
                document.cookie = `accessToken=${res.accessToken}; expires=${expirationDate.toUTCString()}; path=/; secure; SameSite=Strict;`;
                document.cookie = `refreshToken=${res.refreshToken}; expires=${expirationDate.toUTCString()}; path=/; secure; SameSite=Strict;`;
                return true;
            } else {
                if (!res.authenticated) {
                    throw new AuthException('Sua conta está desativada!');
                }
                if (!res.roles.includes('admin')) {
                    throw new AuthException('Você não tem permissão de administrador para acessar o painel!');
                }
                return false;
            }
        }),
        catchError((error: HttpErrorResponse | AuthException) => {
            let errorMessage: string;
            if (error instanceof HttpErrorResponse) {
                if (error.status === 0) {
                    errorMessage = 'Servidor está offline. Tente de novo mais tarde!';
                } else {
                    errorMessage = error.error?.message || 'Ocorreu um erro inesperado';
                }
            } else if (error instanceof AuthException) {
                errorMessage = error.message;
            } else {
                errorMessage = 'Ocorreu um erro inesperado';
            }
            return throwError(() => new AuthException(errorMessage));
        })
    );
}

  refreshToken(){
    const employeeRegistration = getCookie('employeeRegistration');
    const refreshToken = getCookie('refreshToken');
  }

  logout(){
    sessionStorage.removeItem('user');
    deleteCookie('accessToken');
    deleteCookie('refreshToken');
  }

  authenticated(): boolean {
    const accessToken = getCookie('accessToken');
    return !!accessToken;
  }

 
}
