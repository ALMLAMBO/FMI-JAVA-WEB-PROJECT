import {ProgrammeResponse} from "./programme-response";

export class FacultyInfoResponse {
     idInfoResponse: string;
     name: string;
     dean: string;
     address: string;
     programs: ProgrammeResponse[];
}
