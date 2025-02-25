import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminloginService {

  private adminuserURL="http://localhost:8080/adminSignupAndLogin";
  
    constructor(private http:HttpClient) { }
  
    adminloginuser(email: string, password: string) {
  
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      const body = { email, password };
      return this.http.post<any>(`${this.adminuserURL}/loginCheck`, body, { headers });
      
    }
}
