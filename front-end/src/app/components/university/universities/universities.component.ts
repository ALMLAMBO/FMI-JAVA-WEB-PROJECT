import {Component, OnInit} from '@angular/core';
import {UniversityResponse} from "../../../dto/response/university-response";
import {UniversityService} from "../../../services/university.service";

@Component({
  selector: 'app-universities',
  templateUrl: './universities.component.html',
  styleUrl: './universities.component.scss'
})
export class UniversitiesComponent implements OnInit {
  universities: UniversityResponse[];
  
  constructor(private universityService: UniversityService) {}

  ngOnInit(): void {
    this.universityService.getAllObjects()
      .subscribe((universities: UniversityResponse[]) => {
        this.universities = universities;
      });      
  }
}
