import { Component } from '@angular/core';
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-creation',
  templateUrl: './creation.component.html',
  styleUrls: ['./creation.component.css']
})
export class CreationComponent {
  constructor(private dataService: DataService, private router: Router) {}
  couponModel: any = {};
  onSubmit() {
    const tags={
      tag_name: this.couponModel.tag_name
    }
    const coupon = {
      name: this.couponModel.name,
      description: this.couponModel.description,
      price: this.couponModel.price,
      duration: this.couponModel.duration,
      lastUpdateDate: this.couponModel.lastUpdateDate,
      tags:[tags]
    };
    this.dataService.createCoupon(coupon).subscribe(
      (response) => {
        console.log('Coupon created successfully', response, coupon);
        this.router.navigate(['/coupons']);
      },
      (error) => {
        console.error('Error creating coupon', error, coupon);
        this.router.navigate(['/creation']);
      }
    );
  }
}
