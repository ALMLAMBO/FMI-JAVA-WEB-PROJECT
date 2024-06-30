import {DisciplineResponse} from "./discipline-response";
import {ReviewResponse} from "./review-response";

export class UserDetail {
    id: string;
    email: string;
    facultyNumber: string;
    universityName: string;
    facultyName: string;
    programmeName: string;
    disciplines: DisciplineResponse[];
    reviews: ReviewResponse[];
}
