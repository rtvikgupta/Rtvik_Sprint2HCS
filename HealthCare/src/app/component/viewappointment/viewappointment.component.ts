import { Component, OnInit } from '@angular/core';
import { HealthcareService } from 'src/app/service/healthcare.service';
import { Responseappointment } from 'src/app/model/responseappointment';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-viewappointment',
  templateUrl: './viewappointment.component.html',
  styleUrls: ['./viewappointment.component.css']
})
export class ViewappointmentComponent implements OnInit {

  service:HealthcareService;
  showappointments:Array<Responseappointment>=[];
  constructor(service:HealthcareService) {
    this.service = service;
   }

  ngOnInit(): void {
    let result:Observable<Responseappointment[]>=this.service.fetchAppointmentsByUser("1011");
    result.subscribe(a => {
      this.showappointments = a;
    },
    err=>console.log(err)
    ); 
  }
}
