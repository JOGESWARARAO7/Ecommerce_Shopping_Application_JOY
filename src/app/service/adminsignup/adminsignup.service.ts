import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usersigndata } from '../../Class/UserSignData/usersigndata';
import { Observable } from 'rxjs';
import { Adminsignupdata } from '../../Class/AdminSignUpData/adminsignupdata';

@Injectable({
  providedIn: 'root'
})
export class AdminsignupService {

  private probemuserURL="http://localhost:8080/adminSignupAndLogin";
  
    constructor(private http:HttpClient) { }
  
    
    signupuser(admindetails: Adminsignupdata): Observable<any> {  
      
      return this.http.post<any>(`${this.probemuserURL}/adminsignup`, admindetails);
    }
  
    otpcheck(email: string, otp: string) {
      return this.http.get<any>(`${this.probemuserURL}/signUpOtpCheck?email=${email}&otp=${otp}`, {});
    }

}
