import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Responseappointment } from '../model/responseappointment';
import { Observable } from 'rxjs';
import { Createappointment } from '../model/createappointment';
import { Createuser } from '../model/createuser';
import { Test } from '../model/test';
import { Diagnosticcenter } from '../model/diagnosticcenter';
import { ViewUser } from '../model/viewuser';

@Injectable({
  providedIn: 'root'
})
export class HealthcareService {
  client:HttpClient;
  constructor(client:HttpClient) { 
    this.client = client;
  }

  centerTestBaseUrl = "http://localhost:8580/centers";
  appointmentBaseUrl = "http://localhost:8581/appointments" ;
  userBaseUrl = "http://localhost:8582/users" ;

  // Center Test Module

  addCenter(center:Diagnosticcenter):Observable<Diagnosticcenter>{
    let url=this.centerTestBaseUrl+"/addcenter";
    let result:Observable<Diagnosticcenter>=this.client.post<Diagnosticcenter>(url,center);
    return result;
   
  }

  removeCenter(centerId:string):Observable<boolean>{
    let url=this.centerTestBaseUrl+"/remove/center/"+centerId;
   let result:Observable<boolean>=this.client.delete<boolean>(url);
    return result;
  }
  
  fetchAll():Observable<Diagnosticcenter[]>{
    let url=this.centerTestBaseUrl;
    let result:Observable<Diagnosticcenter[]>=this.client.get<Diagnosticcenter[]>(url);
    return result;
  }

  addTest(test:Test,centerId:string):Observable<Test[]>{
    let url=this.centerTestBaseUrl+"/addtest/"+centerId;
    let result:Observable<Test[]>=this.client.put<Test[]>(url,test);
    return result;
  }

  removeTest(testId:string,centerId:string):Observable<boolean>{
    let url=this.centerTestBaseUrl+"/remove/test/"+centerId+"/"+testId;
    let result:Observable<boolean>=this.client.delete<boolean>(url);
    return result;
  }
 
  showTests(centerId:string):Observable<Test[]>{
    let url=this.centerTestBaseUrl+"/show/tests/"+centerId;
    let result:Observable<Test[]>=this.client.get<Test[]>(url);
    return result;
  }
  
  findByCenterId(centerId:string):Observable<Diagnosticcenter>{
    let url=this.centerTestBaseUrl+"/findby/"+centerId;
    let result:Observable<Diagnosticcenter>=this.client.get<Diagnosticcenter>(url);
    return result;
  }

  //Appointment Module

  /**
   * fires post request to server with appointment as body
   * @param a 
   */
  addAppointment(a:Createappointment):Observable<string>
  {
    let url= this.appointmentBaseUrl+"/add";
    let result:Observable<string>= this.client.post<string>(url,a);
    return result;
  }

  /**
   * fires get request to server to fetch all appointments for center-id mentioned in url
   * @param id of center 
   */
  fetchAppointmentsByCenter(id:string):Observable<Responseappointment[]>
  {
    let url = this.appointmentBaseUrl+"/getByCenter/"+id;
    let result:Observable<Responseappointment[]> =this.client.get<Responseappointment[]>(url);
    return result;
  }

  /**
   * fires get request to server to fetch all appointments for user-id mentioned in url
   * @param id of user
   */
  fetchAppointmentsByUser(id:string):Observable<Responseappointment[]>
  {
    let url = this.appointmentBaseUrl+"/getByUser/"+id;
    let result:Observable<Responseappointment[]> =this.client.get<Responseappointment[]>(url);
    return result;
  }

  /**
   * fires get request to server to fetch all appointments for centerid mentioned in url
   * @param id of center 
   */
  approveAppointment(id:string):Observable<boolean>
  {
    let url = this.appointmentBaseUrl+"/approve/"+id;
    let result:Observable<boolean> =this.client.put<boolean>(url,id);
    return result;
  }

  /**
   * fires get request to server to fetch all diagnostic centers
   */
  fetchAllCenters():Observable<Diagnosticcenter[]>
  {
    let url = this.appointmentBaseUrl+"/centers";
    let result:Observable<Diagnosticcenter[]> = this.client.get<Diagnosticcenter[]>(url);
    return result;
  }

  /**
   * fires get request to server to fetch all tests on the basis of diagnostic center id
   * @param id 
   */
  fetchAllTests(id:string):Observable<Test[]>
  {
    let url = this.appointmentBaseUrl+"/tests/"+id;
    let result:Observable<Test[]> = this.client.get<Test[]>(url);
    return result;
  }

  //User Module
  /**
   * fires post request to server with user as body
   * @param a 
   */
  addUser(user:Createuser):Observable<string>
  {
    let url= this.userBaseUrl+"/add";
    let result:Observable<string>= this.client.post<string>(url,user);
    return result;
  }
  
  getUser(id:string):Observable<ViewUser>
  {
    let url= this.userBaseUrl+"/get/"+id;
    let result:Observable<ViewUser>= this.client.get<ViewUser>(url);
    return result;
  }

}
