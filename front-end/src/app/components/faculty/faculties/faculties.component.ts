import {Component, Input} from '@angular/core';
import {FacultyResponse} from "../../../dto/response/faculty-response";

@Component({
  selector: 'app-faculties',
  templateUrl: './faculties.component.html',
  styleUrl: './faculties.component.scss'
})
export class FacultiesComponent {
  @Input() faculties!: FacultyResponse[];

}
