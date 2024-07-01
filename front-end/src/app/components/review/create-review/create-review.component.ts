import {Component, OnInit} from '@angular/core';
import {CreateReviewRequest} from "../../../dto/request/create-review-request";
import {ReviewService} from "../../../services/review.service";
import {UniversityService} from "../../../services/university.service";
import {FacultyService} from "../../../services/faculty.service";
import {ProgrammeService} from "../../../services/programme.service";
import {UniversityResponse} from "../../../dto/response/university-response";
import {FacultyResponse} from "../../../dto/response/faculty-response";
import {ProgrammeResponse} from "../../../dto/response/programme-response";
import {DisciplineResponse} from "../../../dto/response/discipline-response";
import {FormBuilder, Validators} from "@angular/forms";
import {MatSelectChange} from "@angular/material/select";
import {DisciplineService} from "../../../services/discipline.service";
import {response} from "express";

@Component({
  selector: 'app-create-review',
  templateUrl: './create-review.component.html',
  styleUrl: './create-review.component.scss'
})
export class CreateReviewComponent implements OnInit {
  review: CreateReviewRequest = new CreateReviewRequest();
  universities: UniversityResponse[] = [];
  faculties: FacultyResponse[] = [];
  programmes: ProgrammeResponse[] = [];
  disciplines: DisciplineResponse[] = [];
  reviewForm = this.formBuilder.group({
    university: ['', Validators.required],
    faculty: ['', Validators.required],
    programme: ['', Validators.required],
    discipline: ['', Validators.required],
    comment: ['', Validators.required],
    courseRating: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
    lecturerRating: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
    assistantsRating: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
    difficulty: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
    usefulness: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
    workLoad: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
  });

  hasExam: boolean;
  hasProject: boolean;
  hasMidChecks: boolean;
  hasHomeworks: boolean;
  hasOnlineClasses: boolean;
  hasBooks: boolean;
  hasPresentations: boolean;
  hasAdditionalMaterials: boolean;
  
  constructor(private formBuilder: FormBuilder,
              private universityService: UniversityService,
              private facultyService: FacultyService,
              private programmeService: ProgrammeService,
              private disciplineService: DisciplineService,
              private reviewService: ReviewService) {
  }

  ngOnInit(): void {
    this.universityService.getAllObjects()
      .subscribe(universities => {
        this.universities = universities;
      });
  }

  submitReview() {
    if (this.reviewForm.valid) {
      this.review.comment = this.reviewForm.controls.comment.value!;
      this.review.courseRating = +this.reviewForm.controls.courseRating.value!;
      this.review.lecturerRating = +this.reviewForm.controls.lecturerRating.value!;
      this.review.assistantsRating = +this.reviewForm.controls.assistantsRating.value!;
      this.review.difficulty = +this.reviewForm.controls.difficulty.value!;
      this.review.usefulness = +this.reviewForm.controls.usefulness.value!;
      this.review.workLoad = +this.reviewForm.controls.workLoad.value!;
      this.review.hasExam = this.hasExam;
      this.review.hasProject = this.hasProject;
      this.review.hasMidChecks = this.hasMidChecks;
      this.review.hasHomeworks = this.hasHomeworks;
      this.review.hasOnlineClasses = this.hasOnlineClasses;
      this.review.hasBooks = this.hasBooks;
      this.review.hasPresentations = this.hasPresentations;
      this.review.hasAdditionalMaterials = this.hasAdditionalMaterials;

      this.reviewService.createObject(this.review)
        .subscribe(response  => {
          console.log(response)
        });
    }
  }

  updateUniversity($event: MatSelectChange) {
    this.facultyService.getFacultiesForUniversity($event.value)
      .subscribe(faculties => {
        this.faculties = faculties;
      });
  }

  updateFaculty($event: MatSelectChange) {
    this.programmeService.getProgrammesForFaculty($event.value)
      .subscribe(programmes => {
        this.programmes = programmes;
      })
  }

  updateProgramme($event: MatSelectChange) {
    this.disciplineService.getDisciplinesForProgramme($event.value)
      .subscribe(disciplines => {
        this.disciplines = disciplines;
      })
  }
}
