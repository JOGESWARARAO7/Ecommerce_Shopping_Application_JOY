import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Productdto } from '../../Class/productDTO/productdto';
import { Productorder } from '../../Class/productOrder/productorder';

@Injectable({
  providedIn: 'root'
})
export class AddbagService {
 
  

  private addbaglistURL="http://localhost:8080/addBag";
    
      constructor(private http:HttpClient) { }
  
      addProductToAddBag(userId: number, productId: number): Observable<any> {
        return this.http.post<any>(`${this.addbaglistURL}/addToBag`, { userid: userId, productid: productId });
      }

      getalladdbaguserid(userId: number) {
        return this.http.get<Productorder[]>(`${this.addbaglistURL}/user/${userId}`)
      }

      removeproductidanduserid(userId: number, productid: number) {
        return this.http.delete<string>(`${this.addbaglistURL}/deleteToTheBag?userid=${userId}&productid=${productid}`)
      }

}
