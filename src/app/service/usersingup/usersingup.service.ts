import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usersigndata } from '../../Class/UserSignData/usersigndata';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersingupService {


private probemuserURL="http://localhost:8080/signup";

  constructor(private http:HttpClient) { }

  
  signupuser(userdetails: Usersigndata): Observable<any> {  
    
    return this.http.post<any>(`${this.probemuserURL}/signUpData`, userdetails);
  }

  otpcheck(email: string, otp: string) {
    return this.http.get<any>(`${this.probemuserURL}/signUpOtpCheck?email=${email}&otp=${otp}`, {});
  }
  

}
