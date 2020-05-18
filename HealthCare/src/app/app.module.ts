import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminComponent } from './component/admin/admin.component';
import { AddcenterComponent } from './component/addcenter/addcenter.component';
import { RemovecenterComponent } from './component/removecenter/removecenter.component';
import { AddtestComponent } from './component/addtest/addtest.component';
import { RemovetestComponent } from './component/removetest/removetest.component';
import { ApproveappointmentComponent } from './component/approveappointment/approveappointment.component';
import { UserComponent } from './component/user/user.component';
import { TakeappointmentComponent } from './component/takeappointment/takeappointment.component';
import { RegisterComponent } from './component/register/register.component';
import { ViewappointmentComponent } from './component/viewappointment/viewappointment.component';
import { HealthcareService } from './service/healthcare.service';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    AddcenterComponent,
    RemovecenterComponent,
    AddtestComponent,
    RemovetestComponent,
    ApproveappointmentComponent,
    UserComponent,
    TakeappointmentComponent,
    RegisterComponent,
    ViewappointmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [HealthcareService],
  bootstrap: [AppComponent]
})
export class AppModule { }
