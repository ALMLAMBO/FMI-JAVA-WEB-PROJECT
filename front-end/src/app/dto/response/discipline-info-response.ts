import {ReviewResponse} from "./review-response";
import {DisciplineCategory} from "../../vo/discipline-category";
import {DisciplineType} from "../../vo/discipline-type";

export class DisciplineInfoResponse {
    id: string;
    name: string;
    credits: number;
    category: DisciplineCategory;
    type: DisciplineType;
    reviews: ReviewResponse[];
}
