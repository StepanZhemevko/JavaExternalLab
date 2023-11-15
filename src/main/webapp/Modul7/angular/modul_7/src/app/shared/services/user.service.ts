// user.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private userName: string = '';

  setUserName(name: string): void {
    this.userName = name;
  }

  getUserName(): string {
    return this.userName;
  }

}
