import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MakeAppointmentComponent } from './component/make-appointment/make-appointment.component';
import { ApproveAppointmentComponent } from './component/approve-appointment/approve-appointment.component';
import { UserAppointmentComponent } from './component/user-appointment/user-appointment.component';


const routes: Routes = [
  {path:'makeappointment', component: MakeAppointmentComponent},
  {path: 'approveappointment', component: ApproveAppointmentComponent},
  {path: 'userappointment',component: UserAppointmentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
