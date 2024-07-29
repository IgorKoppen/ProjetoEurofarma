import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Document, DocumentResponse } from '../interfaces/documentinterfaces';
import { Observable } from 'rxjs';

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


  findAllDocs() : Observable<DocumentResponse>{
   return this.http.get<DocumentResponse>(this.apiUrl, {headers: this.headers});
  }

  addNewDoc(){

  }

  deleteDoc(docId: string){

  }
}
