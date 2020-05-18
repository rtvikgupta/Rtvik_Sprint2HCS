import { Component, OnInit } from '@angular/core';
import { HealthcareService } from 'src/app/service/healthcare.service';
import { Createappointment } from 'src/app/model/createappointment';
import { Observable } from 'rxjs';
import { Diagnosticcenter } from 'src/app/model/diagnosticcenter';
import { Test } from 'src/app/model/test';

@Component({
  selector: 'app-takeappointment',
  templateUrl: './takeappointment.component.html',
  styleUrls: ['./takeappointment.component.css']
})
export class TakeappointmentComponent implements OnInit {
  
  service:HealthcareService;
  centers:Array<Diagnosticcenter>=[];
  tests:Array<Test>=[];
  min:String;
  appId:string;
  centerId:string;
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

  getTest(){
    let result:Observable<Test[]>=this.service.fetchAllTests(this.centerId);
    result.subscribe(t => {
      this.tests = t;
    },
    err=>console.log(err)
    ); 
  }

  makeAppointment(form:any){
    let details = form.value;
    let centerId = details.center;
    let testId = details.test;
    let date = details.dateTime;
    let datetime = this.getDateTime(date);
    let a = new Createappointment(datetime,"1011",centerId,testId);
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
