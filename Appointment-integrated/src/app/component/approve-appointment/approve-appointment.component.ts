import { Component, OnInit } from '@angular/core';
import { AppointmentService } from 'src/app/service/appointment.service';
import { DummyDataService } from 'src/app/service/dummy-data.service';
import { Center } from 'src/app/model/Center';
import { RequestAppointment } from 'src/app/model/request-appointment';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-approve-appointment',
  templateUrl: './approve-appointment.component.html',
  styleUrls: ['./approve-appointment.component.css']
})
export class ApproveAppointmentComponent implements OnInit {

  service:AppointmentService;
  dummyService:DummyDataService;
  centers:Array<Center>=[];
  showappointments:Array<RequestAppointment>=[];
  appStatus:boolean;
  centerId:String;
  appointmentId:String;

  constructor(service:AppointmentService,dummyService:DummyDataService) {
      this.service = service;
      this.dummyService = dummyService;
   }

  ngOnInit(): void {
    this.centers = this.dummyService.getDummyData();
  }

  getAppointments(){
    this.showappointments=[];
    let result:Observable<RequestAppointment[]>=this.service.fetchAppointmentsByCenter(this.centerId);
    result.subscribe(a => {
      this.showappointments = a;
    },
    err=>console.log(err)
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

  // approve(form){
  //   let details = form.value;
    
  //   let result:Observable<boolean>=this.service.approveAppointment(this.appointmentId);
  //   result.subscribe(a => {
  //     this.appStatus = a;
  //   },
  //   err=>console.log(err)
  //   );
  // }

}
