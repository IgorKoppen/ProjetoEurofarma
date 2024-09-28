import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { getCookie } from '../util/cookieFunction';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Permission } from '../interfaces/permissionInterface';

@Injectable({
  providedIn: 'root'
})
export class PermissionService {

  private baseUrl = `${environment.backEndApiBase.url}/eurofarma/permission`
  private accessToken = getCookie('accessToken') || ""
  private AuthorizationHeader = new HttpHeaders({
    'Accept': 'application/json',
    'Authorization': `Bearer ${this.accessToken}`
  });

  constructor(private http: HttpClient){}

  findAll() : Observable<Permission[]>{
    return this.http.get<Permission[]>(this.baseUrl, { headers: this.AuthorizationHeader});
  }
}
