import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {UniversityService} from "../../../services/university.service";
import {FacultyService} from "../../../services/faculty.service";
import {ProgrammeService} from "../../../services/programme.service";
import {DisciplineService} from "../../../services/discipline.service";
import {MatSelectChange} from "@angular/material/select";
import {UniversityResponse} from "../../../dto/response/university-response";
import {FacultyResponse} from "../../../dto/response/faculty-response";
import {ProgrammeResponse} from "../../../dto/response/programme-response";
import {CreateDisciplineRequest} from "../../../dto/request/create-discipline-request";
import {DisciplineCategory} from "../../../vo/discipline-category";
import {DisciplineType} from "../../../vo/discipline-type";

@Component({
  selector: 'app-create-discipline',
  templateUrl: './create-discipline.component.html',
  styleUrl: './create-discipline.component.scss'
})
export class CreateDisciplineComponent implements OnInit {
  disciplineForm = this.formBuilder.group({
    university: ['', Validators.required],
    faculty: ['', Validators.required],
    programme: ['', Validators.required],
    name: ['', [Validators.required, Validators.minLength(3)]],
    description: ['', [Validators.required, Validators.minLength(3)]],
    credits: ['', Validators.required],
    lecturer: ['', [Validators.required, Validators.minLength(3)]],
    assistants: ['', [Validators.required, Validators.minLength(3)]],
    disciplineCategory: ['', [Validators.required]],
    disciplineType: ['', [Validators.required]]
  });

  discipline: CreateDisciplineRequest = new CreateDisciplineRequest();
  universities: UniversityResponse[] = [];
  faculties: FacultyResponse[] = [];
  programmes: ProgrammeResponse[] = [];

  constructor(private formBuilder: FormBuilder,
              private universityService: UniversityService,
              private facultyService: FacultyService,
              private programmeService: ProgrammeService,
              private disciplineService: DisciplineService) {
  }

  ngOnInit(): void {
    this.universityService.getAllObjects()
      .subscribe(universities => {
        this.universities = universities;
      });
    
    console.log(DisciplineCategory.CSC);
  }

  updateUniversity($event: MatSelectChange) {
    this.facultyService.getFacultiesForUniversity($event.value)
      .subscribe(faculties => {
        this.faculties = faculties;
      })
  }

  updateFaculty($event: MatSelectChange) {
    this.programmeService.getProgrammesForFaculty($event.value)
      .subscribe(programmes => {
        this.programmes = programmes;
      })
  }
  
  submitDiscipline() {
    if (!this.disciplineForm.valid) {
      console.log("Invalid form");
    }
    else {
      this.discipline.name = this.disciplineForm.controls.name.value!;
      this.discipline.description = this.disciplineForm.controls.description.value!;
      this.discipline.credits = +this.disciplineForm.controls.credits.value!;
      this.discipline.lecturer = this.disciplineForm.controls.lecturer.value!;
      this.discipline.assistants = this.disciplineForm.controls.assistants.value!;
      this.discipline.category = <DisciplineCategory>this.disciplineForm.controls.disciplineCategory.value!;
      this.discipline.type = <DisciplineType>this.disciplineForm.controls.disciplineType.value!;
      
      this.disciplineService.createObject(this.discipline)
        .subscribe(response => {
          console.log(response);
        });
    }
  }

  protected readonly DisciplineCategory = DisciplineCategory;
  protected readonly Object = Object;
  protected readonly DisciplineType = DisciplineType;
}
