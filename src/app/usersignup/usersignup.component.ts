import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule,Router} from '@angular/router';
import { Usersigndata } from '../Class/UserSignData/usersigndata';
import { UsersingupService } from '../service/usersingup/usersingup.service';

@Component({
  selector: 'app-usersignup',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule],
  templateUrl: './usersignup.component.html',
  styleUrl: './usersignup.component.css'
})
export class UsersignupComponent implements OnInit{

  userdetails: Usersigndata = new Usersigndata();

  errormessage:string="";

  

  constructor(private router:Router,private signservice:UsersingupService){ }

  onSubmit(): void {
    if (!this.userdetails.username || !this.userdetails.email || !this.userdetails.password) {
      alert("Please fill in all fields.");
      return;
    }
   
    
    this.signservice.signupuser(this.userdetails).subscribe(

      ()=>{
        this.router.navigate(['/userotp'])
      },(error: any) =>{
        this.errormessage=error.error;
      }

    )

  }

  ngOnInit(): void {
    
  }

}
