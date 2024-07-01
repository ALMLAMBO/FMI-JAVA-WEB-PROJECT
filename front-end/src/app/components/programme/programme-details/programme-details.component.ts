import {Component, OnInit} from '@angular/core';
import {ProgrammeInfoResponse} from "../../../dto/response/programme-info-response";
import {ActivatedRoute} from "@angular/router";
import {environment} from "../../../../environments/environment.development";
import {ProgrammeService} from "../../../services/programme.service";
import {response} from "express";

@Component({
  selector: 'app-programme-details',
  templateUrl: './programme-details.component.html',
  styleUrl: './programme-details.component.scss'
})
export class ProgrammeDetailsComponent implements OnInit {
  programme: ProgrammeInfoResponse;
  programmeId: string;
  
  constructor(private route: ActivatedRoute,
              private programmeService: ProgrammeService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.programmeId = params[environment.urlIds.programme];
    })      
    
    this.programmeService.getObjectById(this.programmeId)
      .subscribe(response  => {
        this.programme = response;
      })
  }
}
