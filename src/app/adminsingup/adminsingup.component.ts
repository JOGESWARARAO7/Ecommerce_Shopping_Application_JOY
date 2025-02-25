import { Component } from '@angular/core';
import { UsersingupService } from '../service/usersingup/usersingup.service';
import { Usersigndata } from '../Class/UserSignData/usersigndata';
import { RouterModule,Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Adminsignupdata } from '../Class/AdminSignUpData/adminsignupdata';
import { AdminsignupService } from '../service/adminsignup/adminsignup.service';

@Component({
  selector: 'app-adminsingup',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule],
  templateUrl: './adminsingup.component.html',
  styleUrl: './adminsingup.component.css'
})
export class AdminsingupComponent {

  admindetails: Adminsignupdata = new Adminsignupdata();
  
    errormessage:string="";
  
    
  
    constructor(private router:Router,private signservice:AdminsignupService){ }
  
    onSubmit(): void {
      if (!this.admindetails.adminname || !this.admindetails.email || !this.admindetails.password) {
        alert("Please fill in all fields.");
        return;
      }
     
      
      this.signservice.signupuser(this.admindetails).subscribe(
  
        ()=>{
          this.router.navigate(['/adminotp'])
        },(error: any) =>{
          this.errormessage=error.error;
        }
  
      )
  
    }
  
    ngOnInit(): void {
      
    }
  


}
