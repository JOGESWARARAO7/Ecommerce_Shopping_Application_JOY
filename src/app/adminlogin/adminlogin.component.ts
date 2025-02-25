import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UserloginService } from '../service/userlogin/userlogin.service';
import { AdminloginService } from '../service/adminlogin/adminlogin.service';

@Component({
  selector: 'app-adminlogin',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './adminlogin.component.html',
  styleUrl: './adminlogin.component.css'
})
export class AdminloginComponent implements OnInit{

  email:string='';
      password:string='';
  
      errormessage:string='';
  
      constructor(private router:Router,private adminloginservice:AdminloginService){}
  
      emailPattern: RegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    
      // Password validation pattern
      passwordPattern: RegExp = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{6,}$/;
    
      // Check if both username (email) and password are valid
      get isLoginDisabled(): boolean {
        return !(this.emailPattern.test(this.email) && this.passwordPattern.test(this.password));
      }
    
     
  
  
    login() {
  
      this.adminloginservice.adminloginuser(this.email,this.password).subscribe(
  
        (data:any)=>{
          sessionStorage.setItem('auth_token', data.token); // Store user data in local storage
          this.router.navigate(['/adminaddproduct'])
        },(error: any) =>{
          this.errormessage="Usename And Password Invalid";
          sessionStorage.removeItem('auth_token');
        }
  
      )
      
    }
  
  
    
    ngOnInit(): void {
      
    }

}
