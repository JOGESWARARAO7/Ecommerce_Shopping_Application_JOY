import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule,Router  } from '@angular/router';

import { UserloginService } from '../service/userlogin/userlogin.service';
import { error } from 'console';

@Component({
  selector: 'app-userlogin',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule],
  templateUrl: './userlogin.component.html',
  styleUrl: './userlogin.component.css'
})
export class UserloginComponent implements OnInit{
 
    email:string='';
    password:string='';

    errormessage:string='';

    constructor(private router:Router,private loginservice:UserloginService){}

    emailPattern: RegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  
    // Password validation pattern
    passwordPattern: RegExp = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{6,}$/;
  
    // Check if both username (email) and password are valid
    get isLoginDisabled(): boolean {
      return !(this.emailPattern.test(this.email) && this.passwordPattern.test(this.password));
    }
  
   


  login() {

    this.loginservice.loginuser(this.email,this.password).subscribe(

      (data:any)=>{
        sessionStorage.setItem('auth_token', data.token); // Store user data in local storage
        this.router.navigate(['/userhomepage'])
      },(error: any) =>{
        this.errormessage="Usename And Password Invalid";
        sessionStorage.removeItem('auth_token');
      }

    )
    
  }


  
  ngOnInit(): void {
    
  }

  
  

}
