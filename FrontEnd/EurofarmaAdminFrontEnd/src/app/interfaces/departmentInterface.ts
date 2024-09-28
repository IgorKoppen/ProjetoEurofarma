import { RoleInfo } from "./roleInterface";

export interface Department {
    id?: number;
    departName: string;
    roles: RoleInfo[];
  }
export interface DepartmentInsert{
  departName: string;
}