import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Item} from './item';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  itemsInCart: Item[] = [];
  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  addToCart(item: Item, qty: number) {
    let itemAlreadyInCart = false;
    this.itemsInCart = this.itemsInCart.map(i => {
      if (i.id == item.id) {
        i.quantity += qty;
        itemAlreadyInCart = true;
      }
      return i;
    });

    if (!itemAlreadyInCart) {
      const newItem = new Item(item.name, item.category, item.price, qty, item.quantity, item.imageUrl, item.total,item.imported,item.domestic, item.salesTax, item.importFee);
      newItem.id = item.id;
      this.itemsInCart.push(newItem);
    }
  }

  getCartItems(): Item[] {
    return this.itemsInCart;
  }

  removeItemFromCart(index: number) {
    this.itemsInCart.splice(index, 1);
  }

  emptyCart() {
    this.itemsInCart = [];
  }

  purchase(cartItems: Item[]): Observable<Item > {
    const url = `${this.apiUrl}/purchase`;
    console.log(cartItems[0])
    return this.http.post<Item >(url, cartItems[0]);
  }

}
