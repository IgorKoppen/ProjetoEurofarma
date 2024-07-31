import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { DocumentApiResponse } from '../interfaces/documentInterfaces';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EuroDataChatbotDocsService {

  constructor(private http: HttpClient) {
  }
  private apiUrl = 'https://api.vectara.io/v2/corpora/' + environment.euroDataApi.corpus_key;
  
  private headers = new HttpHeaders({
    'Accept': 'application/json',
    'x-api-key': environment.euroDataApi.api_key
  });

  private headersDelete = new HttpHeaders({
    'x-api-key': environment.euroDataApi.api_key
  });


  findAllDocs() : Observable<DocumentApiResponse>{
   return this.http.get<DocumentApiResponse>(this.apiUrl +'/documents', {headers: this.headers});
  }

  addNewDoc(file: File, metadata: any): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    if (metadata) {
      formData.append('metadata', JSON.stringify(metadata));
    }
    return this.http.post<any>(this.apiUrl + "/upload_file", formData, { headers: this.headers });
  }

  deleteDoc(docId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/documents/${docId}`, { headers: this.headersDelete });
  }
  
}
