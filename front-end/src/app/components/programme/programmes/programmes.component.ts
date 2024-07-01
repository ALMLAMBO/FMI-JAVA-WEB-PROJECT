import {Component, Input} from '@angular/core';
import {ProgrammeResponse} from "../../../dto/response/programme-response";

@Component({
  selector: 'app-programmes',
  templateUrl: './programmes.component.html',
  styleUrl: './programmes.component.scss'
})
export class ProgrammesComponent {
  @Input() programmes: ProgrammeResponse[];

}
