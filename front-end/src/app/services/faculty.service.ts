import { Injectable } from '@angular/core';
import {BaseService} from "./base.service";
import {FacultyResponse} from "../dto/response/faculty-response";
import {FacultyInfoResponse} from "../dto/response/faculty-info-response";
import {CreateFacultyRequest} from "../dto/request/create-faculty-request";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class FacultyService extends BaseService<FacultyResponse, FacultyInfoResponse, CreateFacultyRequest> {
  constructor(private client: HttpClient) {
    super(client);
    this.entityName = environment.entityNames.faculty;
  }
}
