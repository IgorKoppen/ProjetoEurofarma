import { Department } from "./departmentInterface";
import { InstructorInfo } from "./instructorInfoInterface";
import { Links, Page } from "./paginationInterface";
import { Quiz } from "./quizInterface";

export interface Training {
    id: number;
    name: string;
    code: string;
    password: string;
    description: string;
    instructorsInfo: InstructorInfo[];
    tags: Tag[];
    departments?: Department[];
    hasQuiz: boolean;
    quiz?: Quiz;
    opened: boolean;
    creation_date: string;
    closing_date: string;
}

export interface Tag {
    id: number;
    name: string;
    color: string;
}

interface Embedded {
    trainingDTOList: Training[];
}


export interface TrainingPaginationResponse {
    _embedded: Embedded;
    _links: Links;
    page: Page;
}