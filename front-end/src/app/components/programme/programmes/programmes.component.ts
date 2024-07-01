import {Component, Input} from '@angular/core';
import {ProgrammeResponse} from "../../../dto/response/programme-response";
import {ActivatedRoute} from "@angular/router";
import {environment} from "../../../../environments/environment.development";

@Component({
  selector: 'app-programmes',
  templateUrl: './programmes.component.html',
  styleUrl: './programmes.component.scss'
})
export class ProgrammesComponent {
  @Input() programmes: ProgrammeResponse[];
  universityId: string = '';
  facultyId: string = '';

  constructor(private route: ActivatedRoute) {
    this.route.params
      .subscribe(params => {
        this.universityId = params[environment.urlIds.university];
        this.facultyId = params[environment.urlIds.faculty];
      });

  }
}
