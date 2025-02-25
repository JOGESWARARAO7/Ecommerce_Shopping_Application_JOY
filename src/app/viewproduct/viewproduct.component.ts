import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { UserheadderComponent } from '../userheadder/userheadder.component';

import { AddproductService } from '../service/adminproduct/addproduct.service';
import { Productdto } from '../Class/productDTO/productdto';
import { WhishlistService } from '../service/whishlist/whishlist.service';
import { error } from 'node:console';
import { AddbagService } from '../service/addbag/addbag.service';

@Component({
  selector: 'app-viewproduct',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule, UserheadderComponent],
  templateUrl: './viewproduct.component.html',
  styleUrl: './viewproduct.component.css'
})
export class ViewproductComponent implements OnInit{

  selectedImage!: string;
  selectedImageIndex: number = 0;
  images: string[] = [];
  isWishlisted: boolean = false;
  productdto: Productdto = new Productdto();
  userId:number=0;
  
  errormessage:string='';

  constructor( private route: ActivatedRoute, private productservice: AddproductService,private whishlist:WhishlistService,private addbagservice:AddbagService) { }

  ngOnInit(): void {


    const token = sessionStorage.getItem('auth_token');
    if (token) {
      const parsedToken = this.decodeToken(token);
      this.userId = parsedToken.userId || parsedToken.sub || 'User'; // Fallback to 'User' if no username is found
    }



    const productIdParam = this.route.snapshot.paramMap.get('productid');
    if (productIdParam) {
      const productId = parseInt(productIdParam, 10);
    this.productservice.getProductById(productId).subscribe(
      (data: Productdto) => {
        this.productdto = data;

        // Populate images array
        this.images = [
          data.productimage,
          data.productrightsideview,
          data.productleftsideview,
          data.productbacksideview
        ].filter(img => img); // Remove any null values

        // Set default image
        if (this.images.length > 0) {
          this.selectedImage = this.images[0];
        }
      },
      (error) => {
        console.log("Product not found", error);
      }
    );
  }
}

toggleWishlist(productid?: number) {
  if (productid === undefined) {
    this.errormessage = "Product ID is missing.";
    return;
  }
  if(this.userId <1){
    this.errormessage = "user ID is missing.";
    return;
  }
  
  this.isWishlisted = !this.isWishlisted;

  this.whishlist.addProductToWishlist(this.userId, productid).subscribe(
    (response: any) => {
      this.errormessage = response.message;
    },
    (error: any) => {
      this.errormessage = error.error.error || "Failed to add product to wishlist";
    }
  );
}


addtobag(productid?: number){

  if (productid === undefined) {
    this.errormessage = "Product ID is missing.";
    return;
  }
  if(this.userId <1){
    this.errormessage = "user ID is missing.";
    return
  }
  
  this.isWishlisted = !this.isWishlisted;

  this.addbagservice.addProductToAddBag(this.userId, productid).subscribe(
    (response: any) => {
      this.errormessage = response.message;
    },
    (error: any) => {
      this.errormessage = error.error.error || "Failed to add product to wishlist ";
    }
  );

}

  changeImage(index: number) {
    this.selectedImageIndex = index;
    this.selectedImage = this.images[index];
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
  


}