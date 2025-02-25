import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserforgetService {

  private apiUrl="http://localhost:8080/signup";
  
    constructor(private http:HttpClient) { }

    generateOtp(email: string) {
      return this.http.post<any>(`${this.apiUrl}/fpotpgenarated`,{email});
    }
  
    verifyOtp(email: string, otp: number) {
      return this.http.get<any>(`${this.apiUrl}/forgetOtpCheck?email=${email}&otp=${otp}`);
    }
    
  
    updatePassword(email: string, newPassword: string) {
      return this.http.post<any>(`${this.apiUrl}/changepassword`, { email, newPassword });
    }
}
