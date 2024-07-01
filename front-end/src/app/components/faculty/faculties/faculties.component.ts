import {Component, Input, OnInit} from '@angular/core';
import {FacultyResponse} from "../../../dto/response/faculty-response";
import {ActivatedRoute} from "@angular/router";
import {environment} from "../../../../environments/environment.development";

@Component({
  selector: 'app-faculties',
  templateUrl: './faculties.component.html',
  styleUrl: './faculties.component.scss'
})
export class FacultiesComponent implements OnInit {
  @Input() faculties: FacultyResponse[];

  universityId: string = '';

  constructor(private route: ActivatedRoute){

  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.universityId = params[environment.urlIds.university];
    });
  }
}
