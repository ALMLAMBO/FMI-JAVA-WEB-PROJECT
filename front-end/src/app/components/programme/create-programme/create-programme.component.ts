import {Component, OnInit} from '@angular/core';
import {FacultyService} from "../../../services/faculty.service";
import {UniversityService} from "../../../services/university.service";
import {FormBuilder, Validators} from "@angular/forms";
import {UniversityResponse} from "../../../dto/response/university-response";
import {FacultyResponse} from "../../../dto/response/faculty-response";
import {ProgrammeService} from "../../../services/programme.service";
import {MatSelectChange} from "@angular/material/select";
import {CreateProgrammeRequest} from "../../../dto/request/create-programme-request";

@Component({
  selector: 'app-create-programme',
  templateUrl: './create-programme.component.html',
  styleUrl: './create-programme.component.scss'
})
export class CreateProgrammeComponent implements OnInit {
  programmeForm = this.formBuilder.group({
    university: ['', [Validators.required]],
    faculty: ['', [Validators.required]],
    title: ['', [Validators.required, Validators.minLength(3)]],
    description: ['', [Validators.required, Validators.minLength(3)]]
  });
  
  facultyId: string;
  programme: CreateProgrammeRequest = new CreateProgrammeRequest();
  universities: UniversityResponse[] = [];
  faculties: FacultyResponse[] = [];

  constructor(private formBuilder: FormBuilder,
              private universityService: UniversityService,
              private facultyService: FacultyService,
              private programmeService: ProgrammeService) {}

  ngOnInit(): void {
    this.universityService.getAllObjects()
      .subscribe(universities => {
        this.universities = universities;
      })
  }

  submitProgramme() {
    if (!this.programmeForm.valid) {
      console.log("Invalid form");
    }
    else {
      this.programme.title = this.programmeForm.controls.title.value!;
      this.programme.description = this.programmeForm.controls.description.value!;
      this.programme.facultyId = this.facultyId;
      this.programmeService.createObject(this.programme)
        .subscribe(response  => {
          console.log(response);
        })
    }
  }

  updateUniversity($event: MatSelectChange) {
    this.facultyService.getFacultiesForUniversity($event.value)
      .subscribe(faculties => {
        this.faculties = faculties;
      })
  }
}
