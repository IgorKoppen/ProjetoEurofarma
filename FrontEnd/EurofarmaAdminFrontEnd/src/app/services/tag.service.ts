import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { getCookie } from '../util/cookieFunction';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tag } from '../interfaces/trainingInterface';

@Injectable({
  providedIn: 'root'
})
export class TagService {
  private baseUrl = `${environment.backEndApiBase.url}/eurofarma/tag`
  private accessToken = getCookie('accessToken') || ""
  private AuthorizationHeader = new HttpHeaders({
    'Accept': 'application/json',
    'Authorization': `Bearer ${this.accessToken}`,
        'ngrok-skip-browser-warning':'banana'
  });

  constructor(private http: HttpClient) {}

  findAll(): Observable<Tag[]>{
    return this.http.get<Tag[]>(this.baseUrl, { headers: this.AuthorizationHeader});
  }

}
