import { Component } from '@angular/core';
import {AuthService} from "../../../share/services/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {first} from "rxjs";
import {HttpResponse} from "@angular/common/http";
import {CookieManagerService} from "../../../share/services/cookie-manager.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  constructor(private authService:AuthService, private router:Router,
              private cookieManager:CookieManagerService) {
  }

  form= new FormGroup({
    email:new FormControl('',[Validators.email,Validators.required]),
    password:new FormControl('',Validators.required)
  });

  login() {
    this.authService.login(
      this.form.get('email')?.value,
      this.form.get('password')?.value
    ).pipe(first())
      .subscribe((data:HttpResponse<any>)=>{
        this.cookieManager.setCookie(data.headers.get('Authorization')!);
    },error => {
        console.log(error);
      })
  }
}
