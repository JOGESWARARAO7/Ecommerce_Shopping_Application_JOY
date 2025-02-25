import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Productdto } from '../../Class/productDTO/productdto';

@Injectable({
  providedIn: 'root'
})
export class FiltterService {
  
  

  private probemuserURL="http://localhost:8080/filter";
  
    constructor(private http:HttpClient) { }

    getproductbasedonfilter(selectedFilters: { productName: string; price: number; gender: string; color: string; brandName: string }) {
      let params: any = {};
    
      if (selectedFilters.productName) params.productName = selectedFilters.productName;
      if (selectedFilters.price) params.price = selectedFilters.price;
      if (selectedFilters.gender) params.gender = selectedFilters.gender;
      if (selectedFilters.color) params.color = selectedFilters.color;
      if (selectedFilters.brandName) params.brandName = selectedFilters.brandName;
    
      return this.http.get<Productdto[]>(`${this.probemuserURL}/filterproducts`, { params });
    }
    
    

    
}
