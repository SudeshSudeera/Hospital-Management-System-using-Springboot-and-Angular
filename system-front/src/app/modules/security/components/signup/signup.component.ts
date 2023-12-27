import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../share/services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  constructor(private authService:AuthService, private router:Router) {
  }

    form= new FormGroup({
      fullName:new FormControl('',Validators.required),
      email:new FormControl('',[Validators.email,Validators.required]),
      password:new FormControl('',Validators.required)
    });

  signup() {
    this.authService.createUser(
      this.form.get('fullName')?.value,
      this.form.get('email')?.value,
      this.form.get('password')?.value
    ).subscribe(response=>{
      alert('success!');
      this.router.navigateByUrl("/security/login");
    })
  }
}
