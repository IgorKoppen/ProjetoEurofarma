interface EmployeeProcessing {
    message: string;
    rowIndex: number;
  }
  
interface EmployeeRegistrationResponse {
    numberOfEmployeesInserted: number;
    errors: EmployeeProcessing[];
}

  export {EmployeeRegistrationResponse}