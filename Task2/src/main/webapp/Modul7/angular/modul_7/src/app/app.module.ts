import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";
import {CouponComponent} from "./shared/components/coupon/coupon.component";
import {Login} from "./shared/components/login/login.component";
import {RegistrationComponent} from "./shared/components/registration/registration.component";
import {CreationComponent} from "./shared/components/creation/creation.component";
import {OrderComponent} from "./shared/components/order/order.component";
import {PlacingComponent} from "./shared/components/placing/placing.component";
import {DataService} from "./shared/services/data.service";
import { FormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
      { path: 'coupons', component: CouponComponent },
      { path: 'login', component: Login },
      { path: 'registration', component: RegistrationComponent },
      { path: 'creation', component: CreationComponent },
      { path: 'order', component: OrderComponent },
      { path: 'placing', component: PlacingComponent }
    ]),
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [DataService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
