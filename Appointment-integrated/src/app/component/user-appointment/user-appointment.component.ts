import { Component, OnInit } from '@angular/core';
import { AppointmentService } from 'src/app/service/appointment.service';
import { RequestAppointment } from 'src/app/model/request-appointment';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-appointment',
  templateUrl: './user-appointment.component.html',
  styleUrls: ['./user-appointment.component.css']
})
export class UserAppointmentComponent implements OnInit {

  service:AppointmentService;
  showappointments:Array<RequestAppointment>=[];
  constructor(service:AppointmentService) {
    this.service = service;
   }

  ngOnInit(): void {
    let result:Observable<RequestAppointment[]>=this.service.fetchAppointmentsByUser("1011");
    result.subscribe(a => {
      this.showappointments = a;
    },
    err=>console.log(err)
    ); 
  }

}
