import { Component, OnInit } from '@angular/core';
import { Test } from 'src/app/model/test';
import { Diagnosticcenter } from 'src/app/model/diagnosticcenter';
import { HealthcareService } from 'src/app/service/healthcare.service';

@Component({
  selector: 'app-addtest',
  templateUrl: './addtest.component.html',
  styleUrls: ['./addtest.component.css']
})
export class AddtestComponent implements OnInit {

  service:HealthcareService;
  test:Test;
  testArray:Array<Test>=[];
  success=false;
  foundCenter:Diagnosticcenter;
  constructor(service:HealthcareService) {
    this.service = service;
    this.test=new Test();
    this.foundCenter=new Diagnosticcenter();
   
  }
  ngOnInit(): void {
   
  }
 addTest(form:any) {
    let data=form.value;
    let Id=data.centerId;
    let name=data.testName;
    //now to send  test in addTest method  we need to create the object of test
    this.test.testName=name;

    let result1=this.service.addTest(this.test,Id);
    result1.subscribe((testLists:Test[])=>{
      this.testArray=testLists;
      this.success=true;
    })
    let result=this.service.findByCenterId(Id);
    result.subscribe((center:Diagnosticcenter)=>{
      this.foundCenter=center;
    })
    form.reset();
   
 }

}
