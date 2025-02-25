import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Productdto } from '../../Class/productDTO/productdto';

@Injectable({
  providedIn: 'root'
})
export class ProductremoveService {
  

  private probemuserURL="http://localhost:8080/product";
  
    constructor(private http:HttpClient) { }
  
    getallproducts(): Observable<Productdto[]> {
      return this.http.get<Productdto[]>(`${this.probemuserURL}/getallproduct`);
    }
    
    removeproductid(productid: number) {
      return this.http.delete(`${this.probemuserURL}/productremove/${productid}`);
    }
  
}
