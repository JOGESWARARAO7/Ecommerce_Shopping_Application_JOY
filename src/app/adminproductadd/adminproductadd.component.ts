import { CommonModule } from '@angular/common';
import { Component, OnInit, ɵɵqueryRefresh } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AdminheadderComponent } from '../adminheadder/adminheadder.component';
import { ProductDetails } from '../Class/ProductsDetails/product-details';
import { AddproductService } from '../service/adminproduct/addproduct.service';

@Component({
  selector: 'app-adminproductadd',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule,AdminheadderComponent],
  templateUrl: './adminproductadd.component.html',
  styleUrl: './adminproductadd.component.css'
})
export class AdminproductaddComponent implements OnInit{

  product:ProductDetails=new ProductDetails();

  productTypes: string[] = ['Shirt', 'Jeans', 'T-Shirt'];

  productcolor:string[]=['White','Green','Blue','Black','Brown','Pink']

  productBrand:string[]=['Wrong','JOY','Road Star','Allen Solly','Louis Philippe','The Bear Hose']

  errormessage:string='';

  onFileSelected(files: FileList | null, view: string): void {
    if (files && files.length > 0) {
      const file = files[0]; // Get the first selected file
      switch (view) {
        case 'front':
          this.product.productimage = file;
          break;
        case 'right':
          this.product.productrightsideview = file;
          break;
        case 'left':
          this.product.productleftsideview = file;
          break;
        case 'back':
          this.product.productbacksideview = file;
          break;
      }
    }
  }

  constructor(private router:Router,private productserive:AddproductService){

  }

  ngOnInit(): void {
    
  }

  addproduct(event: Event): void {
    event.preventDefault(); // Prevents page refresh
 
    if (!this.product.productname || !this.product.brand || !this.product.price || !this.product.available) {
      this.errormessage = "All fields are required!";
      return;
    }
 
    const formData = new FormData();
    formData.append('productName', this.product.productname);
    formData.append('brand', this.product.brand);
    formData.append('price', this.product.price.toString());
    formData.append('available', this.product.available.toString());
    formData.append('gender', this.product.gender);
    formData.append('productcolor', this.product.productcolor);
 
    // Append product images if they exist
    if (this.product.productimage) {
      formData.append('productImage', this.product.productimage);
    }
    if (this.product.productrightsideview) {
      formData.append('productRightSideView', this.product.productrightsideview);
    }
    if (this.product.productleftsideview) {
      formData.append('productLeftSideView', this.product.productleftsideview);
    }
    if (this.product.productbacksideview) {
      formData.append('productBacksideview', this.product.productbacksideview);
    }
 
    // Send data to backend
    this.productserive.productadd(formData).subscribe(
      () => {
        window.location.reload(); // Reload page on success
      },
      () => {
        this.errormessage = "Product not saved. Retry again!";
      }
    );
  }
 
  

}