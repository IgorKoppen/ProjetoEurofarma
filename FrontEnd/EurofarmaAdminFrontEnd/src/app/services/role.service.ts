import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { getCookie } from '../util/cookieFunction';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Role, RoleInsert } from '../interfaces/roleInterface';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private baseUrl = `${environment.backEndApiBase.url}/eurofarma/role`
  private accessToken = getCookie('accessToken') || ""
  private AuthorizationHeader = new HttpHeaders({
    'Accept': 'application/json',
    'Authorization': `Bearer ${this.accessToken}`,
        'ngrok-skip-browser-warning':'banana'
  });

  constructor(private http: HttpClient){}

  insert(role: RoleInsert) : Observable<Role>{
     return this.http.post<Role>(this.baseUrl,role,  { headers: this.AuthorizationHeader})
  }

  update(id: number,role:Role){
    const roleUpdate = {
      roleName: role.roleName
    };
    return this.http.put<Role>(`${this.baseUrl}/${id}`, roleUpdate,  { headers: this.AuthorizationHeader})
  }

  findAll() : Observable<Role[]>{
    return this.http.get<Role[]>(this.baseUrl, { headers: this.AuthorizationHeader});
  }

  delete(id:number) : Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`, { headers: this.AuthorizationHeader});
  }

}
