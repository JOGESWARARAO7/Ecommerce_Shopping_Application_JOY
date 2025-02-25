import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { FiltterService } from '../service/userfiltter/filtter.service';
import { Productdto } from '../Class/productDTO/productdto';

@Component({
  selector: 'app-filter-popup',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule],
  templateUrl: './filter-popup.component.html',
  styleUrl: './filter-popup.component.css'
})
export class FilterPopupComponent {

  productdto:Productdto []=[];

  @Output() closePopup = new EventEmitter<void>(); 
  @Output() applyFilters = new EventEmitter<any>(); 

  selectedFilters = {
    productName: '',
    brandName:'',
    price:500,
    gender: '',
    color: ''
  };

  productTypes = ['Shirt', 'T-Shirt', 'Jeans', 'Trouser'];
  priceOptions = ['500', '1000', '1500', '2000'];
  genderOptions = ['Male', 'Female', 'Unisex'];
  colorOptions = ['Red', 'Blue', 'Black', 'White', 'Green'];
  brandnames=['Wrong','JOY']

  constructor(private filterservice:FiltterService,private router:Router){

  }


  // Close the Popup
  closeFilterPopup() {
    this.closePopup.emit();
  }


  // Apply Filters
applyFilterSelection() {

  console.log(this.selectedFilters)

  this.filterservice.getproductbasedonfilter(this.selectedFilters).subscribe(
    (response: Productdto[]) => {
      this.productdto = response;
      console.log(this.productdto)
      this.router.navigate(['/filter'], { queryParams: { data: JSON.stringify(this.productdto) }});
    },
    (error) => {
      console.error('Error fetching filtered products:', error);
    }
  );
  this.closeFilterPopup();
}


  

}
