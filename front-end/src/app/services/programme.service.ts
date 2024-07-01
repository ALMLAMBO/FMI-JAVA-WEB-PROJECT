import { Injectable } from '@angular/core';
import {BaseService} from "./base.service";
import {ProgrammeResponse} from "../dto/response/programme-response";
import {ProgrammeInfoResponse} from "../dto/response/programme-info-response";
import {CreateProgrammeRequest} from "../dto/request/create-programme-request";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class ProgrammeService extends BaseService<ProgrammeResponse, ProgrammeInfoResponse, CreateProgrammeRequest> {
  constructor(private client: HttpClient) {
    super(client);
    this.entityName = environment.entityNames.programme;
  }
  
getProgrammesForFaculty(facultyId: string) {
    return this.client.get<ProgrammeResponse[]>(`${environment.baseUrl}/${environment.entityNames.programme}/${facultyId}/programs`);
  }
}
