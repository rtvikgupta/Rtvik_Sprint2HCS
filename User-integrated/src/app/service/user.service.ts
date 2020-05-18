import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs';
import { ViewUser } from '../model/view-user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  client:HttpClient;
  constructor(client:HttpClient) { 
    this.client = client;
  }

  baseUrl = "http://localhost:8581/users" ;

  /**
   * fires post request to server with appointment as body
   * @param a 
   */
  addUser(user:User):Observable<string>
  {
    let url= this.baseUrl+"/add";
    let result:Observable<string>= this.client.post<string>(url,user);
    return result;
  }

  getUser(id:string):Observable<ViewUser>
  {
    let url= this.baseUrl+"/get/"+id;
    let result:Observable<ViewUser>= this.client.get<ViewUser>(url);
    return result;
  }
}
