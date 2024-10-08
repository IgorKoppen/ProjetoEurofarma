import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { getCookie } from '../util/cookieFunction';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TrainingPaginationResponse } from '../interfaces/trainingInterface';
import { FullList } from '../interfaces/presenceList';
import { TrainingSearchParams } from '../interfaces/SearchParamsIntefaces';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {
  private baseUrl = `${environment.backEndApiBase.url}/eurofarma/training`;
  private accessToken = getCookie('accessToken') || "";
  private AuthorizationHeader = new HttpHeaders({
    'Accept': 'application/json',
    'Authorization': `Bearer ${this.accessToken}`,
        'ngrok-skip-browser-warning':'banana'
  });

  constructor(private http: HttpClient) {}


  findAllPagination(page: number = 0, size: number = 10, sort: string = 'id,asc'): Observable<TrainingPaginationResponse> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sort', sort);

    return this.http.get<TrainingPaginationResponse>(`${this.baseUrl}/pagination`, { headers: this.AuthorizationHeader, params });
  }

  search(params: TrainingSearchParams): Observable<TrainingPaginationResponse> {
    let httpParams = new HttpParams();

    if (params.name != null) httpParams = httpParams.set('name', params.name);
    if (params.tagId != null) httpParams = httpParams.set('tagId', params.tagId.toString());
    if (params.tagName != null) httpParams = httpParams.set('tagName', params.tagName);
    if (params.departmentId != null) httpParams = httpParams.set('departmentId', params.departmentId.toString());
    if (params.departmentName != null) httpParams = httpParams.set('departmentName', params.departmentName);
    if (params.instructorRegistration != null) httpParams = httpParams.set('instructorRegistration', params.instructorRegistration.toString());
    if (params.employeeRegistration != null) httpParams = httpParams.set('employeeRegistration', params.employeeRegistration.toString());
    if (params.page != null) httpParams = httpParams.set('page', params.page.toString());
    if (params.size != null) httpParams = httpParams.set('size', params.size.toString());
    if (params.sort != null) httpParams = httpParams.set('sort', params.sort);

    return this.http.get<TrainingPaginationResponse>(`${this.baseUrl}/search`, { headers: this.AuthorizationHeader, params: httpParams });
  }
  findTrainingDetailsById(id: number): Observable<FullList> {
    return this.http.get<FullList>(`${this.baseUrl}/details/${id}`, { headers: this.AuthorizationHeader });
  }

}
