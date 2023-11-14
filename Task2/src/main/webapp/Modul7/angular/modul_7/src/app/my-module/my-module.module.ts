import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MyRoutingModule } from './my-module-routing.module';
import {HttpClientModule} from "@angular/common/http";



@NgModule({
  declarations: [DashboardComponent],
  imports: [
    CommonModule,
    SharedModule,
    MyRoutingModule,
    HttpClientModule
  ]
})
export class MyModuleModule { }
