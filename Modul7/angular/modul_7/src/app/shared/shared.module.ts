import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyComponentComponent } from './my-component/my-component.component';
import { DataService } from './services/data.service';
import { FormsModule } from '@angular/forms';
import { PricePipe } from './pipes/price.pipe';
import { CouponComponent } from './components/coupon/coupon.component';


@NgModule({
  declarations: [MyComponentComponent, PricePipe, CouponComponent],
  imports: [
    CommonModule, FormsModule
  ],
  providers: [DataService],
  exports: [MyComponentComponent,CouponComponent]
})
export class SharedModule { }
