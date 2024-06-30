import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UniversityService} from "../../../services/university.service";
import {UniversityInfoResponse} from "../../../dto/response/university-info-response";
import {environment} from "../../../../environments/environment.development";

@Component({
  selector: 'app-university-details',
  templateUrl: './university-details.component.html',
  styleUrl: './university-details.component.scss'
})
export class UniversityDetailsComponent implements OnInit {
  university: UniversityInfoResponse;
  private universityId: string;
  
  constructor(private route: ActivatedRoute,
              private universityService: UniversityService) { 
    
    this.route.params.subscribe(params => {
      if (params[environment.urlIds.university]) {
        this.universityId = params[environment.urlIds.university];
      }
      else {
        console.log("Id not found");
      }
    });
  }

  ngOnInit(): void {
    if (this.universityId) {
      this.universityService.getObjectById(this.universityId)
        .subscribe((university: UniversityInfoResponse) => {
          this.university = university;
        });
    }
  }
}