import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class DataService {
  constructor(private http: HttpClient) {}
  getDataByService(): Observable<any> /*Add correct type*/ {
    return this.http.get('https://swapi.co/api/people/1');/*Add correcrtUrl */
  }
}
