export interface EmployeeSearchParams {
    name?: string;
    surname?: string;
    employeeRegistration?: number;
    enabled?: boolean;
    roleId?: number;
    roleName?: string;
    permissionId?: string;
    permissionDescription?: string;
    departmentId?: number;
    departmentName?: string;
    page?: number;
    size?: number;
    sort?: string;
  }
  export interface TrainingSearchParams {
    name?: string;
    tagId?: number;
    tagName?: string;
    departmentId?: number;
    departmentName?: string;
    instructorRegistration?: number;
    employeeRegistration?: number;
    page?: number;
    size?: number;
    sort?: string;
}