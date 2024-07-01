import {Component, Input, OnInit} from '@angular/core';
import { DisciplineResponse } from '../../../dto/response/discipline-response';
import {ActivatedRoute} from "@angular/router";
import {environment} from "../../../../environments/environment.development";

@Component({
  selector: 'app-disciplines',
  templateUrl: './disciplines.component.html',
  styleUrl: './disciplines.component.scss'
})
export class DisciplinesComponent implements OnInit {
  @Input() disciplines: DisciplineResponse[] = [];
  universityId: string = '';
  facultyId: string = '';
  programmeId: string = '';

  constructor(private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.universityId = params[environment.urlIds.university];
      this.facultyId = params[environment.urlIds.faculty];
      this.programmeId = params[environment.urlIds.programme];
    });
  }
}
