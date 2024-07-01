import {Component, Input} from '@angular/core';
import {ReviewResponse} from "../../../dto/response/review-response";

@Component({
  selector: 'app-review-details',
  templateUrl: './review-details.component.html',
  styleUrl: './review-details.component.scss'
})
export class ReviewDetailsComponent {
  @Input() review: ReviewResponse;

}
