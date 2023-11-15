import { Component } from '@angular/core';
import {DataService} from "../../services/data.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  constructor(private dataService: DataService, private router: Router) {}
  userModel: any = {};
  onSubmit() {
    const user = {
      name: this.userModel.name,
      password: this.userModel.password,
      login: this.userModel.login,
      email: this.userModel.email,
    };

    this.dataService.createUser(user).subscribe(
      (response) => {
        console.log('User created successfully', response, user);
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Error creating user', error, user);
        this.router.navigate(['/registration']);
      }
    );
  }
}
