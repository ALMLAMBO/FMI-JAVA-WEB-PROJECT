export class ReviewResponse {
    id: string;
    comment: string;
    publishedAt: string;
    courseRating: number;
    lecturerRating: number;
    assistantsRating: number;
    difficulty: number;
    usefulness: number;
    workLoad: number;
    hasExam: boolean;
    hasProject: boolean;
    hasMidChecks: boolean;
    hasHomeworks: boolean;
    hasOnlineClasses: boolean;
    hasBooks: boolean;
    hasPresentations: boolean;
    hasAdditionalMaterials: boolean;
}
