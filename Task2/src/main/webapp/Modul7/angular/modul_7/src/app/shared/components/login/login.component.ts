import {Component} from '@angular/core';
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-my-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class Login {
  constructor(private dataService: DataService,
              private router: Router) {
  }

  onSubmit(loginData: { login: string; password: string }): void {
    this.dataService.getUserByLogin(loginData.login).subscribe(
      (user) => {
        if (user && user.password === loginData.password) {
          localStorage.setItem('userLogin',loginData.login);
          this.router.navigate(['/coupons']);
        } else {
          console.log('Invalid login credentials');
        }
      },
      (error) => {
        console.error('Error during login:', error);
      }
    );
  }

}
