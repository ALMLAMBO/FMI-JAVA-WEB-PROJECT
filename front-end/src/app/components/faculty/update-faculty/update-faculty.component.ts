import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {FacultyService} from "../../../services/faculty.service";
import {UniversityService} from "../../../services/university.service";
import {ActivatedRoute} from "@angular/router";
import {environment} from "../../../../environments/environment.development";
import {CreateFacultyRequest} from "../../../dto/request/create-faculty-request";
import {response} from "express";
import {FacultyInfoResponse} from "../../../dto/response/faculty-info-response";

@Component({
  selector: 'app-update-faculty',
  templateUrl: './update-faculty.component.html',
  styleUrl: './update-faculty.component.scss'
})
export class UpdateFacultyComponent implements OnInit {
  facultyId: string;
  universityId: string;
  faculty: FacultyInfoResponse = new FacultyInfoResponse();
  newFaculty: CreateFacultyRequest = new CreateFacultyRequest();
  universityName: string;

  facultyForm = this.formBuilder.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    dean: ['', [Validators.required, Validators.minLength(3)]],
    address: ['', [Validators.required, Validators.minLength(3)]]
  });

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private facultyService: FacultyService,
              private universityService: UniversityService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.facultyId = params[environment.urlIds.faculty];
      this.universityId = params[environment.urlIds.university];
    })
    
    if (this.facultyId) {
      this.facultyService.getObjectById(this.facultyId)
        .subscribe(response => {
          this.faculty = response;
        })

      this.facultyForm.controls.name.setValue(this.faculty.name);
      this.facultyForm.controls.dean.setValue(this.faculty.dean);
      this.facultyForm.controls.address.setValue(this.faculty.address);

      this.universityService.getObjectById(this.universityId)
        .subscribe(response => {
          this.universityName = response.name;
        })
    }
  }

  submitFaculty() {
    if (!this.facultyForm.valid) {
      console.log("Invalid form");
    } else {
      this.newFaculty.name = this.facultyForm.controls.name.value!;
      this.newFaculty.dean = this.facultyForm.controls.dean.value!;
      this.newFaculty.address = this.facultyForm.controls.address.value!;
      console.log(this.faculty)
      this.facultyService.updateObject(this.facultyId, this.newFaculty)
        .subscribe(response => {
          console.log(response);
        })
    }
  }
}
