import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  public createUser(fullName:any,email:any,password:any):Observable<any>{

    let id=1;
    if (email=='doc@gmail.com'){
      id=2;
    }
    return this.http.post('http://localhost:8000/api/v1/users/visitor/signup',{
      id:id,
      fullName:fullName,
      email:email,
      password:password
    });
  }

  public login(email:any,password:any):Observable<any>{
    return this.http.post<any>('http://localhost:8000/login',{
      username:email,
      password:password
    },
      {observe:'response' as 'body'}
      ).pipe(map(data=>{
        return data;
    }))
  }
public verify(type:string):Observable<any>{
    return this.http.get('http://localhost:8000/api/v1/users/verify?type='+type);
}

//==============Doctor=================
  public saveDoctor(name:any,address:any,salary:any,contact:any):Observable<any>{
    return this.http.post('http://localhost:8000/api/v1/doctors',{
      name:name,
      address:address,
      salary:salary,
      contact:contact
    });
  }
  public getDoctors():Observable<any>{
    return this.http.get('http://localhost:8000/api/v1/doctors/list?searchText=&page=0&size=10');
  }
}
