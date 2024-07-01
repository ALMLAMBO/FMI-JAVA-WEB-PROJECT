import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {CreateFacultyRequest} from "../../../dto/request/create-faculty-request";
import {ActivatedRoute} from "@angular/router";
import {FacultyService} from "../../../services/faculty.service";
import {UniversityService} from "../../../services/university.service";
import {UniversityResponse} from "../../../dto/response/university-response";
import {response} from "express";

@Component({
  selector: 'app-create-faculty',
  templateUrl: './create-faculty.component.html',
  styleUrl: './create-faculty.component.scss'
})
export class CreateFacultyComponent implements OnInit {
  faculty: CreateFacultyRequest = new CreateFacultyRequest();
  universities: UniversityResponse[] = [];
  
  facultyForm = this.formBuilder.group({
    university: ['', [Validators.required]],
    name: ['', [Validators.required, Validators.minLength(3)]],
    dean: ['', [Validators.required, Validators.minLength(3)]],
    address: ['', [Validators.required, Validators.minLength(3)]]
  });

  constructor(private formBuilder: FormBuilder,
              private facultyService: FacultyService,
              private universityService: UniversityService) {
  }

  ngOnInit(): void {
    this.universityService.getAllObjects()
      .subscribe(universities => {
        this.universities = universities;
      })
  }

  submitFaculty() {
    if (!this.facultyForm.valid) {
      console.log("Invalid form");
    }
    else {
      this.faculty.name = this.facultyForm.controls.name.value!;
      this.faculty.dean = this.facultyForm.controls.dean.value!;
      this.faculty.address = this.facultyForm.controls.address.value!;
      console.log(this.faculty)
      this.facultyService.createObject(this.faculty)
        .subscribe(response => {
          console.log(response);
        })
    }
  }
}
