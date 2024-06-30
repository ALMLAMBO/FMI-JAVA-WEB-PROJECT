import { Component } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {UniversityService} from "../../../services/university.service";
import {CreateUniversityRequest} from "../../../dto/request/create-university-request";

@Component({
  selector: 'app-create-university',
  templateUrl: './create-university.component.html',
  styleUrl: './create-university.component.scss'
})
export class CreateUniversityComponent {
  universityForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    rector: ['', [Validators.required, Validators.minLength(3)]],
    hqAddress: ['', [Validators.required, Validators.minLength(3)]]
  });
  
  constructor(private fb: FormBuilder,
              private universityService: UniversityService) { }

  submitUniversity() {
    console.log(this.universityForm.value);
    console.log(this.universityForm.valid);
    if (this.universityForm.valid) {
      this.universityService
        .createObject(this.universityForm.value as CreateUniversityRequest)
        .subscribe((response) => {
          console.log(response);
        });
    }
  }
}
