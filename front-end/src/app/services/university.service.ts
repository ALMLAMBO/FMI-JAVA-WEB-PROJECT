import { Injectable } from '@angular/core';
import {BaseService} from "./base.service";
import {UniversityResponse} from "../dto/response/university-response";
import {UniversityInfoResponse} from "../dto/response/university-info-response";
import {CreateUniversityRequest} from "../dto/request/create-university-request";
import {environment} from "../../environments/environment.development";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UniversityService extends BaseService<UniversityResponse, UniversityInfoResponse, CreateUniversityRequest> {
  constructor(private client: HttpClient) {
    super(client);
    this.entityName = environment.entityNames.university;
  }
}
