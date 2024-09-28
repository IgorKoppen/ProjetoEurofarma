interface Answer {
    id: number;
    answer: string;
    correct: boolean;
    question: number;
}


interface Question {
    id: number;
    question: string;
    answers: Answer[];
}


interface Quiz {
    id: number;
    nome: string;
    description: string;
    notaMinima: number;
    questionsNumber: number;
    questions: Question[];
}

export {Quiz}