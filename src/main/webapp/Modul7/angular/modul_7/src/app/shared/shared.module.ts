import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { Login } from './components/login/login.component';
import { DataService } from './services/data.service';
import { FormsModule } from '@angular/forms';
import { PricePipe } from './pipes/price.pipe';
import { CouponComponent } from './components/coupon/coupon.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { CreationComponent } from './components/creation/creation.component';
import { OrderComponent } from './components/order/order.component';
import { PlacingComponent } from './components/placing/placing.component';


@NgModule({
  declarations: [Login, PricePipe, CouponComponent, RegistrationComponent, CreationComponent, OrderComponent, PlacingComponent],
    imports: [
        CommonModule, FormsModule, NgOptimizedImage
    ],
  providers: [DataService],
  exports: [Login,CouponComponent]
})
export class SharedModule { }
