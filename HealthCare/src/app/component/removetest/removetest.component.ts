import { Component, OnInit } from '@angular/core';
import { HealthcareService } from 'src/app/service/healthcare.service';
import { Diagnosticcenter } from 'src/app/model/diagnosticcenter';

@Component({
  selector: 'app-removetest',
  templateUrl: './removetest.component.html',
  styleUrls: ['./removetest.component.css']
})
export class RemovetestComponent implements OnInit {

  serviceObj:HealthcareService;
  center:Diagnosticcenter;
  removed:boolean;
 
  dc=false;
  constructor(serviceObj:HealthcareService) {
  this.serviceObj=serviceObj;
   }

  ngOnInit(): void {
  }

  removeTestFromCenter(form:any)
  {
    let data=form.value;
    let cId=data.centerid;
    let tId=data.testid;

   
    let result=this.serviceObj.removeTest(tId,cId);
    result.subscribe((variable:boolean)=>{
      this.removed=variable;
     
    },err=>{this.dc=true;
      console.log("err in deleteing test="+err);
     })
     let dc=this.serviceObj.findByCenterId(cId);
     dc.subscribe((dcenter:Diagnosticcenter)=>{
       this.center=dcenter;
     }) 

    
    form.reset();

   
    }

}
