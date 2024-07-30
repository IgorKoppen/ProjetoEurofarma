import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DocumentApiResponse } from '../interfaces/documentInterfaces';
import { environment } from '../../environments/environment';



@Injectable({
  providedIn: 'root'
})
export class EuroComplianceChatbotDocsService {
  constructor(private http: HttpClient) {
  }
  private apiUrl = 'https://api.vectara.io/v2/corpora/' + environment.eurofarmaComplianceApi.corpus_key;
  
  private headers = new HttpHeaders({
    'Accept': 'application/json',
    'x-api-key': environment.eurofarmaComplianceApi.api_key
  });


  findAllDocs() : Observable<DocumentApiResponse>{
   return this.http.get<DocumentApiResponse>(this.apiUrl +'/documents', {headers: this.headers});
  }

  addNewDoc(file: File, metadata: any): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    formData.append('metadata', JSON.stringify(metadata));
    return this.http.post<any>(`${this.apiUrl}/upload_file`, formData, { headers: this.headers })
  }


  deleteDoc(docId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/documents/${docId}`, { headers: this.headers });
  }
}
