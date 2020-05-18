import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './component/admin/admin.component';
import { AddcenterComponent } from './component/addcenter/addcenter.component';
import { RemovecenterComponent } from './component/removecenter/removecenter.component';
import { AddtestComponent } from './component/addtest/addtest.component';
import { RemovetestComponent } from './component/removetest/removetest.component';
import { ApproveappointmentComponent } from './component/approveappointment/approveappointment.component';
import { UserComponent } from './component/user/user.component';
import { RegisterComponent } from './component/register/register.component';
import { TakeappointmentComponent } from './component/takeappointment/takeappointment.component';
import { ViewappointmentComponent } from './component/viewappointment/viewappointment.component';

const routes: Routes = [
  
  {path:"admin",component:AdminComponent,children:[
    {path: "addcenter", component:AddcenterComponent},
    {path: "removecenter", component:RemovecenterComponent},
    {path: "addtest",component:AddtestComponent},
    {path: "removetest", component:RemovetestComponent},
    {path: "approveappointment",component:ApproveappointmentComponent}
    ]
  },

  {path:"user", component:UserComponent, children:[
    {path: "register", component:RegisterComponent},
    {path: "takeappointment", component:TakeappointmentComponent},
    {path: "viewappointment", component:ViewappointmentComponent}
  ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
