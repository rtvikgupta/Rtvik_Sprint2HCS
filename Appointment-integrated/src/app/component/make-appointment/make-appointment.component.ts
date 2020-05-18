import { Component, OnInit } from '@angular/core';
import { AppointmentService } from 'src/app/service/appointment.service';
import { DummyDataService } from 'src/app/service/dummy-data.service';
import { Center } from 'src/app/model/Center';
import { Test } from 'src/app/model/test';
import { ResponseAppointment } from 'src/app/model/response-appointment';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-make-appointment',
  templateUrl: './make-appointment.component.html',
  styleUrls: ['./make-appointment.component.css']
})
export class MakeAppointmentComponent implements OnInit {

  service:AppointmentService
  dummyService:DummyDataService;
  centers:Array<Center>=[];
  tests:Array<Test>=[];
  min:String;
  appId:string;
  centerId:String;
  flag:boolean  = false;

  constructor(service:AppointmentService,dummyService:DummyDataService) {
        this.service = service;
        this.dummyService = dummyService;
   }

  ngOnInit(): void {
    this.centers = this.dummyService.getDummyData(); 
  }

  getTest(){
    this.centers.forEach(c => {
      if(c.id === this.centerId)
        {
          this.tests = c.tests
        }
    });
  }

  makeAppointment(form:any){
    this.flag = false;
    let details = form.value;
    let centerId = details.center;
    let testId = details.test;
    let date = details.dateTime;
    let datetime = this.getDateTime(date);
    let a = new ResponseAppointment(datetime,"1011",centerId,testId);
    let result:Observable<string>=this.service.addAppointment(a);
    result.subscribe(a => {
      this.appId = a;
      this.flag = true;
    },
    err=>console.log(err)
    );
    form.reset();
  }

  getDateTime(date):String {
    let dateTime:String;
    let arr = date.split("-", 3).join(",").split("T",2).join(",").split(":",2).join(",").split(",",5);
    dateTime = arr[2]+"-"+arr[1]+"-"+arr[0]+" "+arr[3]+":"+arr[4];
    return dateTime; 
  }

  getMin(){
    let todayDate = new Date();
    let d = todayDate.getDate();
    let m = todayDate.getMonth()+1;
    let year = todayDate.getFullYear();
    let date;
    let month;
    if(m<10)
      month = "0"+m;
    else
      month = m;
    if(d<10)
      date = "0"+d;
    else
      date = d;
    this.min = year+"-"+month+"-"+date+"T00:00:00";
  }

}
