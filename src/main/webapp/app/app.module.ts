import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatSelectModule, MatInputModule, MatButtonModule, MatGridListModule, MatExpansionModule, MatCheckboxModule, MatCardModule } from '@angular/material';
import { NouisliderModule } from 'ng2-nouislider';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { ActivitiesComponent } from './activities/activities.component';
import { FilterContainerComponent } from './activities/filter-container/filter-container.component';
import { ListContainerComponent } from './activities/list-container/list-container.component';

const routes:Routes=[
  {
    path:'',
    component:HomeComponent
  },
  {
    path:'activities',
    component:ActivitiesComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    ActivitiesComponent,
    FilterContainerComponent,
    ListContainerComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MatToolbarModule,
    MatSelectModule, 
    MatInputModule, 
    MatCheckboxModule,
    MatButtonModule,
    MatGridListModule,
    MatExpansionModule,
    MatCardModule,
    NouisliderModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
