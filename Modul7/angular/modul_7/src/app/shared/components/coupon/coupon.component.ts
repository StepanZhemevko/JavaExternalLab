import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-coupon',
  templateUrl: './coupon.component.html',
  styleUrls: ['./coupon.component.css']
})
export class CouponComponent {
  @Input() coupons:any;
}
