import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable()
export class DataService {
  constructor(private http: HttpClient) {}

  getDataByService(page: number) {
    return this.http.get(`http://localhost:8086/certificate/all/${page}`);
  }
  createUser(user: any): Observable<any> {
    return this.http.post(`http://localhost:8086/user/create`, user);
  }
  getUserByLogin(login: string): Observable<any> {
    return this.http.get(`http://localhost:8086/user/login/${login}`);
  }
  createCoupon(coupon: any): Observable<any> {
    return this.http.post(`http://localhost:8086/certificate/create`, coupon);
  }

  buyCoupon(userLogin: string, couponName: string): Observable<any> {
    const url = `http://localhost:8086/user/buy_by_name/${userLogin}/${couponName}`;
    return this.http.post(url, null);
  }
  getUserCoupons(userLogin: string): Observable<any> {
    const url = `http://localhost:8086/certificate/order/${userLogin}`;
    return this.http.get(url);
  }


}
