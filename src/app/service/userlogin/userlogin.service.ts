import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserloginService {


  private probemuserURL="http://localhost:8080/signup";

  constructor(private http:HttpClient) { }

  loginuser(email: string, password: string) {

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { email, password };
    return this.http.post<any>(`${this.probemuserURL}/loginCheck`, body, { headers });
    
  }

  
}
