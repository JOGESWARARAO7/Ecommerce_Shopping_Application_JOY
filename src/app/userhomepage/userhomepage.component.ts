import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule,Router  } from '@angular/router';
import { UserheadderComponent } from '../userheadder/userheadder.component';
import { Productdto } from '../Class/productDTO/productdto';
import { AddproductService } from '../service/adminproduct/addproduct.service';


@Component({
  selector: 'app-userhomepage',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule,UserheadderComponent],
  templateUrl: './userhomepage.component.html',
  styleUrl: './userhomepage.component.css'
})
export class UserhomepageComponent implements OnInit{

  productdto:Productdto []=[];

  constructor(private productservice:AddproductService,private router:Router){}

  ngOnInit(): void {
    this.productservice.getallproducts().subscribe(
      (data: Productdto[]) => {
        this.productdto = data;  // ✅ Assigning the full array
      },
      (error) => {
        console.error("Error fetching products:", error); // ✅ Handling errors
      }
    );
  }

  
  
  

}
