import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router  } from '@angular/router';
import { UserforgetService } from '../service/userforget/userforget.service';
import { response} from 'express';

@Component({
  selector: 'app-userforgetpassword',
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule],
  templateUrl: './userforgetpassword.component.html',
  styleUrl: './userforgetpassword.component.css'
})
export class UserforgetpasswordComponent implements OnInit{

  email: string = '';
  otp: number = 0;
  newPassword: string = '';
  confirmPassword: string = '';
  successMessage: string = '';
  errorMessage: string = '';
  isEmailDisabled: boolean = false;
  isOtpGenerated: boolean = false;
  isOtpVerified: boolean = false;

  constructor(private userForgetService: UserforgetService,private router:Router) {}
  ngOnInit(): void {
    
  }

  generateOtp() {

    this.userForgetService.generateOtp(this.email).subscribe(
      (data: any) => {
        this.successMessage = 'OTP has been sent to your email.';
        this.errorMessage = '';
        this.isOtpGenerated = true;
        this.isEmailDisabled = true;
      },
      (error: any) => {
        this.errorMessage = 'Failed to send OTP. Try again.';
        this.successMessage = '';
      }
    );
  }

  verifyOtp() {
    alert(this.email+".."+this.otp)
    this.userForgetService.verifyOtp(this.email, this.otp).subscribe(
      (data: any) => {
        this.successMessage = 'OTP verified successfully.';
        this.errorMessage = '';
        this.isOtpVerified = true;
      },
      (error: any) => {
        this.errorMessage = 'Invalid OTP. Try again.';
        this.successMessage = '';
      }
    );
  }

  updatePassword() {
    this.userForgetService.updatePassword(this.email, this.newPassword).subscribe(
      (data: any) => {
        this.router.navigate(['/userloginpage']);
      },
      (error: any) => {
        this.errorMessage = 'Failed to update password. Try again.';
        this.successMessage = '';
      }
    );
  }
}
