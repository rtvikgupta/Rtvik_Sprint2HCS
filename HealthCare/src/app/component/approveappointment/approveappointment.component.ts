import { Component, OnInit } from '@angular/core';
import { HealthcareService } from 'src/app/service/healthcare.service';
import { Responseappointment } from 'src/app/model/responseappointment';
import { Observable } from 'rxjs';
import { Diagnosticcenter } from 'src/app/model/diagnosticcenter';

@Component({
  selector: 'app-approveappointment',
  templateUrl: './approveappointment.component.html',
  styleUrls: ['./approveappointment.component.css']
})
export class ApproveappointmentComponent implements OnInit {

  service:HealthcareService;
  centers:Array<Diagnosticcenter>=[];
  showappointments:Array<Responseappointment>=[];
  appStatus:boolean;
  centerId:string;
  appointmentId:string;
  flag:boolean = false;

  constructor(service:HealthcareService) {
      this.service = service;
   }

  ngOnInit(): void {
    let result:Observable<Diagnosticcenter[]>=this.service.fetchAllCenters();
    result.subscribe(dc => {
      this.centers = dc;
    },
    err=>console.log(err)
    );
  }

  getAppointments(){
    this.flag = false;
    this.showappointments=[];
    let result:Observable<Responseappointment[]>=this.service.fetchAppointmentsByCenter(this.centerId);
    result.subscribe(a => {
      this.showappointments = a;
    },
    err=>
    {
      console.log(err);
      if(this.showappointments.length === 0)
        this.flag = true;
    }
    );
  }

  approve(Id){
    let id = Id.value;
    console.log(id);
    let result:Observable<boolean>=this.service.approveAppointment(id);
    result.subscribe(a => {
      this.appStatus = a;
    },
    err=>console.log(err)
    );
  }
}
