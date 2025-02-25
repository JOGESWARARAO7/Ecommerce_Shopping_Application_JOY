import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AdminheadderComponent } from '../adminheadder/adminheadder.component';
import { Productdto } from '../Class/productDTO/productdto';
import { ProductremoveService } from '../service/adminproductremove/productremove.service';

@Component({
  selector: 'app-adminproductremove',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule,AdminheadderComponent],
  templateUrl: './adminproductremove.component.html',
  styleUrl: './adminproductremove.component.css'
})
export class AdminproductremoveComponent implements OnInit{


  productdto:Productdto[]=[]

  errormessage:string='';

    constructor(private router:Router,private productremove:ProductremoveService,private cdr: ChangeDetectorRef){}

    ngOnInit(): void {
      this.productremove.getallproducts().subscribe(
        (data: Productdto[]) => {
          this.productdto = data;  // ✅ Assigning the full array
        },
        (error) => {
          console.error("Error fetching products:", error); // ✅ Handling errors
        }
      );
    }

    removeproduct(productid?: number) {
      if (productid === undefined) {
        this.errormessage = "Product ID is missing.";
        return;
      }
    
      this.productremove.removeproductid(productid).subscribe(
        (response: any) => { // Expecting a JSON object with a "message" field
          if (response.message === "Success") {
            
            this.productdto = this.productdto.filter(product => product.productid !== productid);
            this.cdr.detectChanges();
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
