import {FacultyResponse} from "./faculty-response";

export class UniversityInfoResponse {
    idInfoResponse: string;
    name: string;
    rector: string;
    hqAddress: string;
    faculties: FacultyResponse[];
}
