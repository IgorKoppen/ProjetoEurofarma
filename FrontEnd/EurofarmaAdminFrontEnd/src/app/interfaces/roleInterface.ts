import { Department } from "./departmentInterface";

export interface Role {
    id?: number;
    roleName: string;
    department: Department;
  }