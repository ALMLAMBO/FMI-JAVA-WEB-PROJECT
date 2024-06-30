import {ReviewResponse} from "./review-response";

export class DisciplineInfoResponse {
    id: string;
    name: string;
    credits: number;
    category: DisciplineCategory;
    type: DisciplineType;
    reviews: ReviewResponse[];
}
