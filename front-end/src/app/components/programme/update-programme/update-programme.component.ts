import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {FacultyService} from "../../../services/faculty.service";
import {UniversityService} from "../../../services/university.service";
import {environment} from "../../../../environments/environment.development";
import {CreateProgrammeRequest} from "../../../dto/request/create-programme-request";
import {ProgrammeService} from "../../../services/programme.service";

@Component({
  selector: 'app-update-programme',
  templateUrl: './update-programme.component.html',
  styleUrl: './update-programme.component.scss'
})
export class UpdateProgrammeComponent implements OnInit {
  programmeId: string;
  newProgramme: CreateProgrammeRequest = new CreateProgrammeRequest();

  programmeForm = this.formBuilder.group({
    title: ['', [Validators.required, Validators.minLength(3)]],
    description: ['', [Validators.required, Validators.minLength(3)]]
  });

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private programmeService: ProgrammeService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.programmeId = params[environment.urlIds.programme];
    })

    if (this.programmeId) {
      this.programmeService.getObjectById(this.programmeId)
        .subscribe(response => {
          this.programmeForm.controls.title.setValue(response.title);
          this.programmeForm.controls.description.setValue(response.description);
        })
    }
  }

  submitProgramme() {
    if (!this.programmeForm.valid) {
      console.log("Invalid form");
    } else {
      this.newProgramme.title = this.programmeForm.controls.title.value!;
      this.newProgramme.description = this.programmeForm.controls.description.value!;
      this.programmeService.updateObject(this.programmeId, this.newProgramme)
        .subscribe(response => {
          console.log(response);
        })
    }
  }
}
