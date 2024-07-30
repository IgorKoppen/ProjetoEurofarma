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
  private apiUrl = 'https://api.vectara.io/v2/corpora/' + environment.eurofarmaComplianceApi.corpus_key +'/documents';
  
  private headers = new HttpHeaders({
    'Accept': 'application/json',
    'x-api-key': environment.eurofarmaComplianceApi.api_key
  });


  findAllDocs() : Observable<DocumentApiResponse>{
   return this.http.get<DocumentApiResponse>(this.apiUrl, {headers: this.headers});
  }

  addNewDoc(){

  }

  deleteDoc(docId: string){
  
    this.http.delete<void>(`${this.apiUrl}/${docId}`, { headers: this.headers }).subscribe(() => {

      console.log(`Document with ID ${docId} deleted successfully`);
    }),
    (error: any) => {
      // Handle error
      if (error.status === 404) {
        console.error('Document not found or already deleted.');
      } else if (error.status === 403) {
        console.error('Authorization error. Check your API key and permissions.');
      } else {
        console.error('An error occurred:', error);
      }
    }
  }
}
