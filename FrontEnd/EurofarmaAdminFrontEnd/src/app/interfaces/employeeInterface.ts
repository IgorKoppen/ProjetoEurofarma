import { Permission } from "./permissionInterface";
import { Role, RoleInfo } from "./roleInterface";

 interface Employee {
    id?: number;
    name: string;
    surname: string;
    enabled?: boolean;
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
    cellphoneNumber: string;
    roleId: RoleInfo;
    permissionsIds: Permission[];
}

interface EmployeeInsert {
    name: string;
    surname: string;
    roleId: number;
    permissionsIds: number[];
    employeeRegistration: number;
    cellphoneNumber: string;
}
export {Employee,EmployeeUpdate,EmployeeInsert}