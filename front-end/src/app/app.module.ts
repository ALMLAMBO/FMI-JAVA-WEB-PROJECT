import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {MatCard, MatCardActions, MatCardContent, MatCardHeader} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import { CreateUniversityComponent } from './components/university/create-university/create-university.component';
import {ReactiveFormsModule} from "@angular/forms";
import {provideHttpClient} from "@angular/common/http";
import { UniversitiesComponent } from './components/university/universities/universities.component';
import { UniversityDetailsComponent } from './components/university/university-details/university-details.component';
import { UpdateUniversityComponent } from './components/university/update-university/update-university.component';
import { FacultiesComponent } from './components/faculty/faculties/faculties.component';
import { FacultyDetailsComponent } from './components/faculty/faculty-details/faculty-details.component';
import { CreateFacultyComponent } from './components/faculty/create-faculty/create-faculty.component';
import { UpdateFacultyComponent } from './components/faculty/update-faculty/update-faculty.component';
import { ProgrammesComponent } from './components/programme/programmes/programmes.component';
import { ProgrammeDetailsComponent } from './components/programme/programme-details/programme-details.component';
import { CreateProgrammeComponent } from './components/programme/create-programme/create-programme.component';
import { UpdateProgrammeComponent } from './components/programme/update-programme/update-programme.component';
import { DisciplinesComponent } from './components/discipline/disciplines/disciplines.component';
import { DisciplineDetailsComponent } from './components/discipline/discipline-details/discipline-details.component';
import { CreateDisciplineComponent } from './components/discipline/create-discipline/create-discipline.component';
import { UpdateDisciplineComponent } from './components/discipline/update-discipline/update-discipline.component';
import { ReviewsComponent } from './components/review/reviews/reviews.component';
import { ReviewDetailsComponent } from './components/review/review-details/review-details.component';
import { CreateReviewComponent } from './components/review/create-review/create-review.component';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { UserDetailsComponent } from './components/user/user-details/user-details.component';
import { HomeComponent } from './components/main/home/home.component';
import { NavbarComponent } from './components/main/navbar/navbar.component';
import {MatDivider} from "@angular/material/divider";
import {MatOption, MatSelect} from "@angular/material/select";

@NgModule({
  declarations: [
    AppComponent,
    CreateUniversityComponent,
    UniversitiesComponent,
    UniversityDetailsComponent,
    UpdateUniversityComponent,
    FacultiesComponent,
    FacultyDetailsComponent,
    CreateFacultyComponent,
    UpdateFacultyComponent,
    ProgrammesComponent,
    ProgrammeDetailsComponent,
    CreateProgrammeComponent,
    UpdateProgrammeComponent,
    DisciplinesComponent,
    DisciplineDetailsComponent,
    CreateDisciplineComponent,
    UpdateDisciplineComponent,
    ReviewsComponent,
    ReviewDetailsComponent,
    CreateReviewComponent,
    LoginComponent,
    RegisterComponent,
    UserDetailsComponent,
    HomeComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCard,
    MatButton,
    MatFormField,
    MatInput,
    MatLabel,
    ReactiveFormsModule,
    MatCardHeader,
    MatCardContent,
    MatDivider,
    MatCardActions,
    MatSelect,
    MatOption
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    provideHttpClient()
  ],
  bootstrap: [AppComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class AppModule { }
