import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { getCookie } from '../util/cookieFunction';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Department } from '../interfaces/departmentInterface';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private baseUrl = `${environment.backEndApiBase.url}/eurofarma/department`
  private accessToken = getCookie('accessToken') || ""
  private AuthorizationHeader = new HttpHeaders({
    'Accept': 'application/json',
    'Authorization': `Bearer ${this.accessToken}`,
    'ngrok-skip-browser-warning':'banana'
  });
 
  constructor(private http: HttpClient){}

 insert(departmentInsert: Department): Observable<Department>{
    return this.http.post<Department>(this.baseUrl,departmentInsert, {headers: this.AuthorizationHeader});
 }

 update(id:number,updateDepartment: Department): Observable<Department>{
  return this.http.put<Department>(`${this.baseUrl}/${id}`,updateDepartment, {headers: this.AuthorizationHeader});
 }
 findById(id:number) : Observable<Department>{
  return this.http.get<Department>(`${this.baseUrl}/${id}`, {headers: this.AuthorizationHeader});
 }

 findAll(): Observable<Department[]>{
  return this.http.get<Department[]>(this.baseUrl, {headers: this.AuthorizationHeader});
 }

 delete(id:number): Observable<void>{
  return this.http.delete<void>(`${this.baseUrl}/${id}`, {headers: this.AuthorizationHeader});
 }

}
