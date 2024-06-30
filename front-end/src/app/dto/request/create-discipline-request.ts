import {DisciplineCategory} from "../../vo/discipline-category";
import {DisciplineType} from "../../vo/discipline-type";

export class CreateDisciplineRequest {
    name: string;
    description: string;
    credits: number;
    category: DisciplineCategory;
    type: DisciplineType;
    lecturer: string;
    assistants: string;
    programmeId: string;
}
