import {Component, OnInit} from '@angular/core';
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-placing',
  templateUrl: './placing.component.html',
  styleUrls: ['./placing.component.css']
})
export class PlacingComponent implements OnInit{
  userCoupons: any[] = [];
constructor(private dataService: DataService, private router: Router)  {}

  ngOnInit(){
    const userLogin = localStorage.getItem('userLogin');
    console.log('User Login:', userLogin);
    if (userLogin) {
      this.dataService.getUserCoupons(userLogin).subscribe(
        (coupons: any[]) => {
          this.userCoupons = coupons;
        },
        error => {
          console.error('Error fetching user coupons:', error);
        }
      );
    }
  }
  getTotalPrice(): number {
    return this.userCoupons.reduce((total, coupon) => total + coupon.price, 0);
  }

  onCklick() {
    this.router.navigate(['/coupons']);
  }
}
