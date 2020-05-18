import { Component, OnInit } from '@angular/core';
import { HealthcareService } from 'src/app/service/healthcare.service';
import { Diagnosticcenter } from 'src/app/model/diagnosticcenter';

@Component({
  selector: 'app-removecenter',
  templateUrl: './removecenter.component.html',
  styleUrls: ['./removecenter.component.css']
})
export class RemovecenterComponent implements OnInit {

  service:HealthcareService;
  removed=false;
  deleted=null;
  dc=null;
  foundCenter:Diagnosticcenter;
  
  constructor(service:HealthcareService) {
    this.service=service;
    this.foundCenter=new Diagnosticcenter();
   }
  
  ngOnInit(): void {
  }

   removeCenter(form:any){
     let data=form.value;
     let id=data.centerId;
    //fing center by center id
     let result=this.service.findByCenterId(id);
     result.subscribe((center:Diagnosticcenter)=>{
      this.foundCenter=center;
     })

     //checks whether
     let result2=this.service.removeCenter(id);
     result2.subscribe((simpleVariable:boolean)=>{
      this.deleted=simpleVariable;
      this.removed=true;
     },err=>{this.dc=true;
      console.log("center not deleted because it not exists"+err);
   })

  form.reset();
  }

}
