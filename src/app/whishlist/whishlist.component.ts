import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UserheadderComponent } from '../userheadder/userheadder.component';
import { Productdto } from '../Class/productDTO/productdto';
import { WhishlistService } from '../service/whishlist/whishlist.service';
import { error } from 'console';

@Component({
  selector: 'app-whishlist',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule,UserheadderComponent],
  templateUrl: './whishlist.component.html',
  styleUrl: './whishlist.component.css'
})
export class WhishlistComponent implements OnInit{

  userId:number=0;

  productdto: Productdto []= [];

  errormessage:string='';

  constructor(private whishlist:WhishlistService){}

  ngOnInit(): void {
    const token = sessionStorage.getItem('auth_token');
    if (token) {
      const parsedToken = this.decodeToken(token);
      this.userId = parsedToken.userId || parsedToken.sub || 'User'; // Fallback to 'User' if no username is found
    }


    
      this.whishlist.getallwhishlistuserid(this.userId).subscribe(
        (data: Productdto[]) => {
          this.productdto = data;  // ✅ Assigning the full array
        },
        (error: any) => {
          console.error("Error fetching products:", error); // ✅ Handling errors
        }
      );


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


  
  removeproduct(productid?: number) {
    if (productid === undefined) {
      this.errormessage = "Product ID is missing.";
      return;
    }
  
    this.whishlist.removeproductidanduserid(this.userId, productid).subscribe(
      (response: any) => { // Expecting a JSON object with a "message" field
        if (response.message === "Success") {
          
          this.productdto = this.productdto.filter(product => product.productid !== productid); // ✅ Remove the product from UI
        } else {
          alert(response.message); // Show backend error message
        }
      },
      (error: any) => {
        this.errormessage = "Failed to remove product: " + (error.error?.message || "Unknown error");
      }
    );
  }
  

}
