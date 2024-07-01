import {Component, OnInit} from '@angular/core';
import {DisciplineInfoResponse} from "../../../dto/response/discipline-info-response";
import {DisciplineService} from "../../../services/discipline.service";
import {ActivatedRoute} from "@angular/router";
import {environment} from "../../../../environments/environment.development";

@Component({
  selector: 'app-discipline-details',
  templateUrl: './discipline-details.component.html',
  styleUrl: './discipline-details.component.scss'
})
export class DisciplineDetailsComponent implements OnInit {
  discipline: DisciplineInfoResponse;

  constructor(private route: ActivatedRoute,
              private disciplineService: DisciplineService) {

  }

  ngOnInit(): void {
    let disciplineId = '';
    this.route.params.subscribe(params => {
      disciplineId = params[environment.urlIds.discipline];
    });

    this.disciplineService.getObjectById(disciplineId)
      .subscribe((data: DisciplineInfoResponse) => {
        this.discipline = data;
      });
  }
}
