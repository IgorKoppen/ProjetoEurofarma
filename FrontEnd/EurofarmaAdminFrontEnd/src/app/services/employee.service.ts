import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { getCookie } from '../util/cookieFunction';
import { Observable } from 'rxjs';
import { EmployeePaginationResponse } from '../interfaces/employeePagination';
import { EmployeeSearchParams } from '../interfaces/SearchParamsIntefaces';
import { Employee, EmployeeInsert, EmployeeUpdate } from '../interfaces/employeeInterface';
import { Permission } from '../interfaces/permissionInterface';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = `${environment.backEndApiBase.url}/eurofarma/employee`
  private accessToken = getCookie('accessToken') || ""
  private AuthorizationHeader = new HttpHeaders({
    'Accept': 'application/json',
    'Authorization': `Bearer ${this.accessToken}`
  });

  constructor(private http: HttpClient){}

  insert(employee: EmployeeInsert): Observable<Employee> {
    return this.http.post<Employee>(`${this.baseUrl}`, employee, {
      headers: this.AuthorizationHeader
    })}

    bulkInsertWithExcel(file: File): Observable<any> {
      const formData: FormData = new FormData();
      formData.append('file', file); 
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${this.accessToken}`,

      });

      return this.http.post(`${this.baseUrl}/createAllEmployees`, formData, {
        headers: headers
      });
    }

    update(employeeId: number, employeeUpdateData: EmployeeUpdate): Observable<Employee> {
      return this.http.put<Employee>(`${this.baseUrl}/${employeeId}`, employeeUpdateData, {
        headers: this.AuthorizationHeader
      });
    }

  delete(id:number){
    return this.http.delete<void>(`${this.baseUrl}/${id}`, { headers: this.AuthorizationHeader});
  }

  disable(id:number){
      return this.http.patch<void>(`${this.baseUrl}/disable/${id}`, null, { headers: this.AuthorizationHeader});
  }

  findAllWithPagination(page: number, size: number, sort: string): Observable<EmployeePaginationResponse> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sort', sort);
    return this.http.get<EmployeePaginationResponse>(`${this.baseUrl}/pagination`, { headers: this.AuthorizationHeader, params });
  }

  search(params: EmployeeSearchParams): Observable<EmployeePaginationResponse> {
    let httpParams = new HttpParams();
    
    if (params.name != null) httpParams = httpParams.set('name', params.name);
    if (params.surname != null) httpParams = httpParams.set('surname', params.surname);
    if (params.employeeRegistration != null) httpParams = httpParams.set('employeeRegistration', params.employeeRegistration.toString());
    if (params.enabled != null) httpParams = httpParams.set('enabled', params.enabled.toString());
    if (params.roleId != null) httpParams = httpParams.set('roleId', params.roleId.toString());
    if (params.roleName != null) httpParams = httpParams.set('roleName', params.roleName);
    if (params.permissionId != null) httpParams = httpParams.set('permissionId', params.permissionId);
    if (params.permissionDescription != null) httpParams = httpParams.set('permissionDescription', params.permissionDescription);
    if (params.departmentId != null) httpParams = httpParams.set('departmentId', params.departmentId.toString());
    if (params.departmentName != null) httpParams = httpParams.set('departmentName', params.departmentName);
    if (params.page != null) httpParams = httpParams.set('page', params.page.toString());
    if (params.size != null) httpParams = httpParams.set('size', params.size.toString());
    if (params.sort != null) httpParams = httpParams.set('sort', params.sort);

    return this.http.get<EmployeePaginationResponse>(`${this.baseUrl}/search`, { headers: this.AuthorizationHeader, params: httpParams });
  }
}
