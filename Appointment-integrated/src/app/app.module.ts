import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MakeAppointmentComponent } from './component/make-appointment/make-appointment.component';
import { ApproveAppointmentComponent } from './component/approve-appointment/approve-appointment.component';
import { UserAppointmentComponent } from './component/user-appointment/user-appointment.component';
import { AppointmentService } from './service/appointment.service';

@NgModule({
  declarations: [
    AppComponent,
    MakeAppointmentComponent,
    ApproveAppointmentComponent,
    UserAppointmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AppointmentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
