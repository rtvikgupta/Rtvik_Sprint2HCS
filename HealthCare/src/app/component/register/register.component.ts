import { Component, OnInit } from '@angular/core';
import { HealthcareService } from 'src/app/service/healthcare.service';
import { Createuser } from 'src/app/model/createuser';
import { Observable } from 'rxjs';
import { ViewUser } from 'src/app/model/viewuser';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  service:HealthcareService;
  userid:string;
  viewuser:ViewUser = null;
  flag:boolean = false;

  constructor(service:HealthcareService) { 
    this.service = service;
  }

  ngOnInit(): void {
  }

  submit(userform:any) {
      let details = userform.value;
      let name = details.name;
      let password = details.password;
      let email = details.email;
      let contact = details.contact;
      let age = details.age;
      let gender = details.gender;
      let user = new Createuser(password,name,contact,email,age,gender);
      console.log(user);
      
      let result:Observable<string>=this.service.addUser(user);
      result.subscribe((id:string) => {        
        this.userid = id;
        this.view();

      },
      err=>console.log(err)
      );
      this.flag=true;
  }

  view() {
    let id = this.userid.toString();
    let result1:Observable<ViewUser>=this.service.getUser(id);
      result1.subscribe(view => {
      this.viewuser = view;
      },
      err=>console.log(err)
      );
  }
}
