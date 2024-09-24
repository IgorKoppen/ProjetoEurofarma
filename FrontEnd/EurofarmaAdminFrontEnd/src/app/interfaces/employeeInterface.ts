import { Permission } from "./permissionInterface";
import { Role } from "./roleInterface";

 interface Employee {
    id: number;
    name: string;
    surname: string;
    enabled: boolean;
    role: Role;
    instructorId?: number; 
    permission: Permission[];
    permissionsDescription?: string[];
    employee_registration: number;
    cellphone_number: string;
  }
interface EmployeeUpdate {
  name: string;
    surname: string;
  cellphone_number: string;
  role: Role;
  permission: Permission[];
}
export {Employee,EmployeeUpdate}