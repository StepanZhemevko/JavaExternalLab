
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {DataService} from "../../services/data.service";


@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  couponName!: string;
  couponDescription!: string;
  couponPrice!: number;
  couponValidTo!: string;

  constructor(
    private route: ActivatedRoute,
    private dataService: DataService,
    private router: Router
  ) {
    this.route.params.subscribe(params => {
      this.couponName = params['name'];
      this.couponDescription = params['description'];
      this.couponPrice = params['price'];
      this.couponValidTo = params['lastUpdateData'];
    });
  }

  ngOnInit(): void {
    const userLogin = localStorage.getItem('userLogin');
    console.log('User Login:', userLogin);
  }

  addToCart(): void {
    const userLogin = localStorage.getItem('userLogin');
    if (userLogin) {
      this.dataService.buyCoupon(userLogin, this.couponName).subscribe(
        response => {
          console.log('Purchase successful:', response);
          this.router.navigate(['/coupons']);
        },
        error => {
          console.error('Error during purchase:', error);
          this.router.navigate(['/order']);
        }
      );
    }
  }

}
