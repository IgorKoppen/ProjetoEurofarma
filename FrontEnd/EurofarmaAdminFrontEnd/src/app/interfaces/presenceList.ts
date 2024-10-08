import { ShortInstructorInfo } from "./instructorInfoInterface";


export interface Attendance {
    id: number;
    name: string;
    surname: string;
    employeeRegistration: number;
    signature: string;
    registrationDate: string;
    quizTries?: number | null;
    nota?: number | null;
}

export interface FullList {
    instructors: ShortInstructorInfo[];
    attendanceList: Attendance[];
}