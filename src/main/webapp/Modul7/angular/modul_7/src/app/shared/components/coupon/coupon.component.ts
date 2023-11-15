import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';

interface Coupon {
  name: string;
  description: string;
  price: number;
  lastUpdateData: string;
}

@Component({
  selector: 'app-coupon',
  templateUrl: './coupon.component.html',
  styleUrls: ['./coupon.component.css']
})
export class CouponComponent implements OnInit, OnChanges {
  @Input() coupons: Coupon[] = [];
  searchTerm: string = '';
  filteredCoupons: Coupon[] = [];

  infiniteScrollActive = false;

  scrollToTop(): void {
    window.scrollTo(0, 0);
  }

  windowScrolled = false;

  constructor(private router: Router) {}

  ngOnInit() {
    window.addEventListener('scroll', () => {
      this.windowScrolled = window.pageYOffset !== 0;
      this.checkInfiniteScroll();
    });
    const userLogin = localStorage.getItem('userLogin');
    console.log('User Login:', userLogin);
    this.filteredCoupons = this.coupons;
  }

  ngOnChanges(changes: SimpleChanges) {
    if ((changes as any).coupons) {
      if (this.infiniteScrollActive) {
        this.filteredCoupons.push(...this.coupons);
        this.infiniteScrollActive = false;
      } else {
        this.filteredCoupons = this.coupons;
      }
    }
  }

  search() {
    const searchTerm = this.searchTerm.toLowerCase();

    this.filteredCoupons = this.coupons.filter(coupon => {
      const name = coupon.name.toLowerCase();
      return name.includes(searchTerm);
    });
  }

  checkInfiniteScroll() {
    const scrollHeight = document.documentElement.scrollHeight;
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    const clientHeight = document.documentElement.clientHeight;

    const scrollThreshold = 100;

    if (scrollHeight - (scrollTop + clientHeight) < scrollThreshold) {
      this.infiniteScrollActive = true;
    }
  }

  redirectToOrder(coupon: Coupon) {
    this.router.navigate(['/order', coupon]);
  }
}
