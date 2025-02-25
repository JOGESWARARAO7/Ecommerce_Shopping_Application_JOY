import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Productorder } from '../Class/productOrder/productorder';
import { UserheadderComponent } from '../userheadder/userheadder.component';

@Component({
  selector: 'app-checkroductdetails',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule,UserheadderComponent],
  templateUrl: './checkroductdetails.component.html',
  styleUrl: './checkroductdetails.component.css'
})
export class CheckroductdetailsComponent implements OnInit{


  selectedProducts: Productorder[] = [];
  totalAmount: number = 0;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.selectedProducts = history.state.selectedProducts || [];
    this.totalAmount = this.selectedProducts.reduce((sum, product) => sum + product.price * product.quantity, 0);
  }

  

}
