import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AdminsignupService } from '../service/adminsignup/adminsignup.service';

@Component({
  selector: 'app-adminotp',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule],
  templateUrl: './adminotp.component.html',
  styleUrl: './adminotp.component.css'
})
export class AdminotpComponent implements OnInit{

  email: string = "";
    otp: string = "";
    timeLeft: number = 180;
    minutes: number = 3;
    seconds: number = 0;
    interval: any;
  
    constructor(private router: Router,private signupservice:AdminsignupService) {}
  
    ngOnInit(): void {
      throw new Error('Method not implemented.');
    }
  
    ngAfterViewInit(): void {
      setTimeout(() => this.startTimer(), 0);
    }
  
    startTimer() {
      this.interval = setInterval(() => {
        if (this.timeLeft > 0) {
          this.timeLeft--;
          this.minutes = Math.floor(this.timeLeft / 60);
          this.seconds = this.timeLeft % 60;
        } else {
          clearInterval(this.interval);
          this.router.navigate(['/usersignup']);
        }
      }, 1000);
    }
  
    verifyOtp() {
      if (this.otp.length === 6) {
        
        this.signupservice.otpcheck(this.email,this.otp).subscribe(
  
          ()=>{
            this.router.navigate(['/adminloginpage'])
          },(error:any)=>{
  
            this.router.navigate(['/adminsignuppage']);
          }
  
        )
  
      } else {
        alert("Invalid OTP. Please try again.");
      }
    }

}
