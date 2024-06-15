import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { GamesComponent } from './components/games/games.component';

import { HttpClientModule } from '@angular/common/http';

import { NgFor } from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';

import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CreateAccountComponent } from './components/create-account/create-account.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { NavbarComponent } from './components/navbar/navbar.component';

import {MatMenuModule} from '@angular/material/menu';
import { DashboardComponent } from './components/dashboard/dashboard.component';

import {MatCardModule} from '@angular/material/card';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    GamesComponent,
    LoginComponent,
    CreateAccountComponent,
    NavbarComponent,
    DashboardComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgFor,
    MatButtonModule,
    NoopAnimationsModule,
    MatTableModule,
    MatIconModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatTooltipModule,
    MatMenuModule,
    MatCardModule
    
  ],
  providers: [HttpClientModule, LoginComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
