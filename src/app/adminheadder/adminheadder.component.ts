import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-adminheadder',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './adminheadder.component.html',
  styleUrl: './adminheadder.component.css'
})
export class AdminheadderComponent implements OnInit{

   adminname: string = ''; // Fetch from user service
    showProfileMenu: boolean = false;
    showFilterPopup: boolean = false;
   
  
    constructor(private router:Router){
  
    }
  
    ngOnInit(): void {
      const token = sessionStorage.getItem('auth_token');
      if (token) {
        const parsedToken = this.decodeToken(token);
        this.adminname = parsedToken.username || parsedToken.sub || 'User'; // Fallback to 'User' if no username is found
      }
    }
    
    
    
    private decodeToken(token: string): any {
      try {
        const payload = token.split('.')[1]; // Extract the payload
        const decodedPayload = JSON.parse(atob(payload)); // Decode the base64 payload
        return decodedPayload; // Return the full decoded token
      } catch (error) {
        console.error("Failed to decode token", error);
        return {};
      }
    }
    
  
    toggleProfileMenu() {
      this.showProfileMenu = !this.showProfileMenu;
    }
  
   
  
  
    logout(){
      sessionStorage.removeItem('auth_token');
      this.router.navigate(["/adminloginpage"]);
    }
  
   


}
