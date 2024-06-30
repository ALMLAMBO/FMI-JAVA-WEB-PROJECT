import {DisciplineCategory} from "../../vo/discipline-category";
import {DisciplineType} from "../../vo/discipline-type";

export class DisciplineResponse {
      id: string;
      name: string;
      credits: number;
      category: DisciplineCategory;
      type: DisciplineType;
      lecturer: string;
      assistants: string;
}
