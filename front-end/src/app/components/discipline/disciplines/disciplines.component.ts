import {Component, Input} from '@angular/core';
import { DisciplineResponse } from '../../../dto/response/discipline-response';

@Component({
  selector: 'app-disciplines',
  templateUrl: './disciplines.component.html',
  styleUrl: './disciplines.component.scss'
})
export class DisciplinesComponent {
  @Input() disciplines!: DisciplineResponse[];

}
