import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Productdto } from '../../Class/productDTO/productdto';
import { Observable } from 'rxjs';
import { ProductDetails } from '../../Class/ProductsDetails/product-details';

@Injectable({
  providedIn: 'root'
})
export class AddproductService {
  
  
private probemuserURL="http://localhost:8080/product";

  constructor(private http:HttpClient) { }

  getallproducts(): Observable<Productdto[]> {
    return this.http.get<Productdto[]>(`${this.probemuserURL}/getallproduct`);
  }
  getProductById(productid: number): Observable<Productdto> {
    return this.http.get<Productdto>(`${this.probemuserURL}/getproductbyid/${productid}`);
  }

  productadd(formData: FormData): Observable<any> {
    return this.http.post(`${this.probemuserURL}/productadd`, formData);
  }
  


}
