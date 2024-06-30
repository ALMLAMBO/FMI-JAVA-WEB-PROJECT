import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {MatCard} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import { CreateUniversityComponent } from './components/university/create-university/create-university.component';
import {ReactiveFormsModule} from "@angular/forms";
import {provideHttpClient} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    CreateUniversityComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCard,
    MatButton,
    MatFormField,
    MatInput,
    MatLabel,
    ReactiveFormsModule
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
