import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Productdto } from '../Class/productDTO/productdto';
import { UserheadderComponent } from '../userheadder/userheadder.component';

@Component({
  selector: 'app-filter',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule,UserheadderComponent],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent {

productdto:Productdto []=[];

constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['data']) {
        this.productdto = JSON.parse(params['data']);
      }
    });
  }

}
