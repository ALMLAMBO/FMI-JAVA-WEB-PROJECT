import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {UniversityService} from "../../../services/university.service";
import {CreateUniversityRequest} from "../../../dto/request/create-university-request";
import {ActivatedRoute} from "@angular/router";
import {environment} from "../../../../environments/environment.development";
import {UniversityInfoResponse} from "../../../dto/response/university-info-response";

@Component({
  selector: 'app-update-university',
  templateUrl: './update-university.component.html',
  styleUrl: './update-university.component.scss'
})
export class UpdateUniversityComponent implements OnInit {
  universityId: string;
  
  universityForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    rector: ['', [Validators.required, Validators.minLength(3)]],
    hqAddress: ['', [Validators.required, Validators.minLength(3)]]
  });

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              private universityService: UniversityService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.universityId = params[environment.urlIds.university];
    });
    
    if (this.universityId) {
      this.universityService.getObjectById(this.universityId)
        .subscribe((university: UniversityInfoResponse) => {
          this.universityForm.controls.name.setValue(university.name);
          this.universityForm.controls.rector.setValue(university.rector);
          this.universityForm.controls.hqAddress.setValue(university.hqAddress);
        });
    }
  }

  submitUniversity() {
    if (this.universityForm.valid) {
      this.universityService
        .updateObject(this.universityId, this.universityForm.value as CreateUniversityRequest)
        .subscribe((response) => {
          console.log(response);
        });
    }
  }
}
