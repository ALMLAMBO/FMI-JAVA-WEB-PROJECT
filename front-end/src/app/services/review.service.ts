import { Injectable } from '@angular/core';
import {BaseService} from "./base.service";
import {ReviewResponse} from "../dto/response/review-response";
import {CreateReviewRequest} from "../dto/request/create-review-request";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class ReviewService extends BaseService<ReviewResponse, ReviewResponse, CreateReviewRequest> {
  constructor(private client: HttpClient) {
    super(client);
    this.entityName = environment.entityNames.review;
  }
  
  getReviewsForDiscipline(disciplineId: string) {
    return this.client.get<ReviewResponse[]>(`${environment.baseUrl}/${environment.entityNames.review}/${disciplineId}/reviews`);
  }
}
