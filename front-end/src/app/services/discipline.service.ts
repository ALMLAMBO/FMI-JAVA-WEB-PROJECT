import { Injectable } from '@angular/core';
import {BaseService} from "./base.service";
import {DisciplineResponse} from "../dto/response/discipline-response";
import {DisciplineInfoResponse} from "../dto/response/discipline-info-response";
import {CreateDisciplineRequest} from "../dto/request/create-discipline-request";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class DisciplineService extends BaseService<DisciplineResponse, DisciplineInfoResponse, CreateDisciplineRequest> {
  constructor(private client: HttpClient) {
    super(client);
    this.entityName = environment.entityNames.discipline;
  }
  
  getDisciplinesForProgramme(programmeId: string) {
    return this.client.get<DisciplineResponse[]>(`${environment.baseUrl}/${this.entityName}/${programmeId}/discipline`);
  }
}
