import { Component } from '@angular/core';
import {FacultyInfoResponse} from "../../../dto/response/faculty-info-response";
import {ActivatedRoute} from "@angular/router";
import {FacultyService} from "../../../services/faculty.service";
import {environment} from "../../../../environments/environment.development";

@Component({
  selector: 'app-faculty-details',
  templateUrl: './faculty-details.component.html',
  styleUrl: './faculty-details.component.scss'
})
export class FacultyDetailsComponent {
  faculty: FacultyInfoResponse;

  constructor(private route: ActivatedRoute,
              private facultyService: FacultyService) {}

  ngOnInit(): void {
    let facultyId = '';
    this.route.params
      .subscribe(params => {
        facultyId = params[environment.urlIds.faculty];
      });

    this.facultyService.getObjectById(facultyId)
      .subscribe((data: FacultyInfoResponse) => {
        this.faculty = data;
      })
  }
}
