import { Department } from "./departmentInterface";

export interface Role {
    id?: number;
    roleName: string;
    department: Department;
  }
export interface RoleInfo {
  id?: number;
  roleName?: string;
}
export interface RoleInsert {
  departmentId: number;
  name: string;
}