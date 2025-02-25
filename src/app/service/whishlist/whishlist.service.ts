import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Productdto } from '../../Class/productDTO/productdto';

@Injectable({
  providedIn: 'root'
})
export class WhishlistService {
 

  private wishlistURL="http://localhost:8080/wishlist";
  
    constructor(private http:HttpClient) { }

    addProductToWishlist(userId: number, productId: number): Observable<any> {
      return this.http.post<any>(`${this.wishlistURL}/addwhislist`, { userid: userId, productid: productId });
    }

    getallwhishlistuserid(userId: number) {
      return this.http.get<Productdto[]>(`${this.wishlistURL}/user/${userId}`)
    }

    removeproductidanduserid(userId: number, productid: number) {
      return this.http.delete<string>(`${this.wishlistURL}/deletewishlist?userid=${userId}&productid=${productid}`)
    }

}
