import { Employee } from "./employeeInterface";
import { Links, Page } from "./paginationInterface";

 interface EmbeddedEmployeeInfo {
    employeeInfoDTOList: Employee[];
  }
  
  
   interface EmployeePaginationResponse {
    _embedded: EmbeddedEmployeeInfo;
    _links: Links;
    page: Page;
  }

 

  export {EmbeddedEmployeeInfo,EmployeePaginationResponse}