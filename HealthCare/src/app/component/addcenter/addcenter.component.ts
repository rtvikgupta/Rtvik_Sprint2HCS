import { Component, OnInit } from '@angular/core';
import { HealthcareService } from 'src/app/service/healthcare.service';
import { Diagnosticcenter } from 'src/app/model/Diagnosticcenter';

@Component({
  selector: 'app-addcenter',
  templateUrl: './addcenter.component.html',
  styleUrls: ['./addcenter.component.css']
})
export class AddcenterComponent implements OnInit {

  service:HealthcareService;
  addedCenter:Diagnosticcenter;
  centerArray:Array<Diagnosticcenter>=[];


  constructor(service:HealthcareService) {
    this.service = service;
   }

   added=false;
   dc=false;

  ngOnInit(): void {
  }

 addCenter(form:any) {
   this.added = false;
   this.dc = false;
    let data=form.value;
    let name=data.centerName;
    let cAddress=data.centerAddress;
    let cNo=data.centerNumber;
    
    this.addedCenter=new Diagnosticcenter();
    this.addedCenter.centerName=name;
    this.addedCenter.address=cAddress;
    this.addedCenter.contactNo=cNo;

    let  result=this.service.addCenter(this.addedCenter);
    
    result.subscribe((center:Diagnosticcenter)=>{
     this.addedCenter=center;
     this.centerArray.push(center);
     this.dc=true;

     },err=>{
       this.added = true;
      console.log(err);
      
      //  console.log("err in adding center"+err);
      })
    form.reset();
    }
}
