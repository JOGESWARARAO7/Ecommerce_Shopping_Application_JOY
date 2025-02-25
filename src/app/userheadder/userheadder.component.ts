import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule,Router } from '@angular/router';
import { FilterPopupComponent } from '../filter-popup/filter-popup.component';


@Component({
  selector: 'app-userheadder',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule,FilterPopupComponent],
  templateUrl: './userheadder.component.html',
  styleUrl: './userheadder.component.css'
})
export class UserheadderComponent implements OnInit{

  username: string = ''; // Fetch from user service
  showProfileMenu: boolean = false;
  showFilterPopup: boolean = false;
 

  constructor(private router:Router){

  }

  ngOnInit(): void {
    const token = sessionStorage.getItem('auth_token');
    if (token) {
      const parsedToken = this.decodeToken(token);
      this.username = parsedToken.username || parsedToken.sub || 'User'; // Fallback to 'User' if no username is found
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

  openFilterPopup() {
    this.showFilterPopup = true;
  }

  closeFilterPopup() {
    this.showFilterPopup = false;
  }


  logout(){
    sessionStorage.removeItem('auth_token');
    this.router.navigate(["/userloginpage"]);
  }

  // Apply filters and close the popup
  applyFilter(filterData: any) {
    console.log("Filters Applied:", filterData);
    this.closeFilterPopup();
  }

}
