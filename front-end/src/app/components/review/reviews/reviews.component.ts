import {Component, Input} from '@angular/core';
import { ReviewResponse } from '../../../dto/response/review-response';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrl: './reviews.component.scss'
})
export class ReviewsComponent {
  @Input() reviews: ReviewResponse[];

}
