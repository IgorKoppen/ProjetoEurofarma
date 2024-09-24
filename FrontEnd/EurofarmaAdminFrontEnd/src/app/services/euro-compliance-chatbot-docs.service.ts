import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { DocumentApiResponse } from '../interfaces/documentInterface';
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

  private headersDelete = new HttpHeaders({
    'x-api-key': environment.eurofarmaComplianceApi.api_key
  });

  findAllDocs() : Observable<DocumentApiResponse>{
   return this.http.get<DocumentApiResponse>(this.apiUrl +'/documents?limit=100', {headers: this.headers});
  }
  addNewDoc(file: File, documentId: string): Observable<any> {
    const formData: FormData = new FormData();

    const sanitizedFileName = file.name.replace(/[^a-zA-Z0-9.\-]/g, '');

    formData.append('file', file, sanitizedFileName);
    formData.append('document_id', documentId); 

    return this.http.post<any>(`${this.apiUrl}/upload_file`, formData, { headers: this.headers }).pipe(
      catchError(error => {
        return throwError(() => new Error(error));
      })
    );
  }

  deleteDoc(docId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/documents/${encodeURIComponent(docId)}`, { headers: this.headersDelete }).pipe(
      catchError(error => {
        return throwError(() => new Error(error));
      })
    );
}
}
