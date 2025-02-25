import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UserheadderComponent } from '../userheadder/userheadder.component';
import { Productdto } from '../Class/productDTO/productdto';
import { AddbagService } from '../service/addbag/addbag.service';
import { Productorder } from '../Class/productOrder/productorder';

@Component({
  selector: 'app-addabag',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule,UserheadderComponent],
  templateUrl: './addabag.component.html',
  styleUrl: './addabag.component.css'
})
export class AddabagComponent implements OnInit{

  productdto: Productorder[] = [];
  selectedProducts: Productorder[] = [];
  userId: number = 0;
  errormessage:string='';

  constructor(private router: Router, private addbagservice: AddbagService) {}

  ngOnInit(): void {
    const token = sessionStorage.getItem('auth_token');
    if (token) {
      const parsedToken = this.decodeToken(token);
      this.userId = parsedToken.userId || parsedToken.sub || 0;
    }

    this.addbagservice.getalladdbaguserid(this.userId).subscribe(
      (data: Productorder[]) => {
        this.productdto = data.map(product => ({ ...product, quantity: 1 })); // Default quantity = 1
      },
      (error: any) => console.error("Error fetching products:", error)
    );
  }

  private decodeToken(token: string): any {
    try {
      const payload = token.split('.')[1];
      return JSON.parse(atob(payload));
    } catch (error) {
      console.error("Failed to decode token", error);
      return {};
    }
  }

  incrementQuantity(product: Productorder) {
    if(product.quantity < 3){
    product.quantity++;
    }
  }

  decrementQuantity(product: Productorder) {
    if (product.quantity > 1) {
      product.quantity--;
    }
  }

  toggleSelection(product: Productorder) {
    if (this.selectedProducts.includes(product)) {
      this.selectedProducts = this.selectedProducts.filter(p => p !== product);
    } else {
      this.selectedProducts.push(product);
    }
  }

  getTotalAmount(): number {
    return this.selectedProducts.reduce((total, product) => total + product.price * product.quantity, 0);
  }

  navigateToCheckout() {
    this.router.navigate(['/checkout'], { state: { selectedProducts: this.selectedProducts } });
  }



  removeproduct(productid?: number) {
    if (productid === undefined) {
      this.errormessage = "Product ID is missing.";
      return;
    }
  
    this.addbagservice.removeproductidanduserid(this.userId, productid).subscribe(
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
