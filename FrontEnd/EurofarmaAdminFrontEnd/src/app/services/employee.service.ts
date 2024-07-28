import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
 

  getAll(){
    return [
      {
        id: 1,
        nome: "Moses",
        sobrenome: "Slany",
        username: "MosesS8906",
        telefone: "967-706-8906",
        status: false,
        cargo: "Librarian",
        departamento: "Training",
        permissões: [
          "Admin",
          "Delete",
          "Write",
          "Update",
          "Read"
        ]
      },
      {
        id: 1,
        nome: "Moses",
        sobrenome: "Slany",
        username: "MosesS8906",
        telefone: "967-706-8906",
        status: false,
        cargo: "Librarian",
        departamento: "Training",
        permissões: [
          "Admin",
          "Delete",
          "Write",
          "Update",
          "Read"
        ]
      },
      {
        id: 1,
        nome: "Moses",
        sobrenome: "Slany",
        username: "MosesS8906",
        telefone: "967-706-8906",
        status: false,
        cargo: "Librarian",
        departamento: "Training",
        permissões: [
          "Admin",
          "Delete",
          "Write",
          "Update",
          "Read"
        ]
      },
    ]
    }
  }

