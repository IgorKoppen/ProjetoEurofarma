import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { getCookie } from '../util/cookieFunction';
import { Observable } from 'rxjs';
import { EmployeePaginationResponse } from '../interfaces/employeePagination';
import { EmployeeSearchParams } from '../interfaces/SearchParamsIntefaces';
import { Employee, EmployeeUpdate } from '../interfaces/employeeInterface';
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

  insert(employee: Employee): Observable<Employee> {
    const employeeData = {
      employee_registration: employee.employee_registration,
      name: employee.name,
      surname: employee.surname,
      cellphone_number: employee.cellphone_number,
      role: { id: employee.role.id },
      permission: employee.permission.map((permission: Permission) => { permission.id })
    };

    return this.http.post<Employee>(`${this.baseUrl}`, employeeData, {
      headers: this.AuthorizationHeader
    })}

    update(employeeId: number, employeeUpdateData: EmployeeUpdate): Observable<Employee> {
      const employeeData = {
        name: employeeUpdateData.name,
        surname: employeeUpdateData.surname,
        cellphone_number: employeeUpdateData.cellphone_number,
        role: { id: employeeUpdateData.role.id },
        permission: employeeUpdateData.permission.map((permission: Permission) => { permission.id })
      };
      return this.http.put<Employee>(`${this.baseUrl}/${employeeId}`, employeeData, {
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
    
    if (params.name) httpParams = httpParams.set('name', params.name);
    if (params.surname) httpParams = httpParams.set('surname', params.surname);
    if (params.employeeRegistration !== undefined) httpParams = httpParams.set('employeeRegistration', params.employeeRegistration.toString());
    if (params.enabled !== undefined) httpParams = httpParams.set('enabled', params.enabled.toString());
    if (params.roleId !== undefined) httpParams = httpParams.set('roleId', params.roleId.toString());
    if (params.roleName) httpParams = httpParams.set('roleName', params.roleName);
    if (params.permissionId) httpParams = httpParams.set('permissionId', params.permissionId);
    if (params.permissionDescription) httpParams = httpParams.set('permissionDescription', params.permissionDescription);
    if (params.departmentId !== undefined) httpParams = httpParams.set('departmentId', params.departmentId.toString());
    if (params.departmentName) httpParams = httpParams.set('departmentName', params.departmentName);
    if (params.page !== undefined) httpParams = httpParams.set('page', params.page.toString());
    if (params.size !== undefined) httpParams = httpParams.set('size', params.size.toString());
    if (params.sort) httpParams = httpParams.set('sort', params.sort);

    return this.http.get<EmployeePaginationResponse>(`${this.baseUrl}/search`, { headers: this.AuthorizationHeader, params: httpParams });
  }
}
