import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Item } from '../item';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
itemsInCart: Item[] = [];
  total = 0;
  infoText = "No items in cart";
  apiUrl = "";
  itemsPurchased :Item [] = [];
  purchased: boolean= false;

  constructor(private cartService: CartService, private router: Router) { }

  ngOnInit() {
    this.getItemsInCart();
    this.calculateTotal();
  }

  onRemoveItemFromCart(index: number) {
    this.cartService.removeItemFromCart(index);
    this.getItemsInCart();
    this.calculateTotal();
  }

  getItemsInCart() {
    this.itemsInCart = this.cartService.getCartItems();
  }

  onPurchase() { 
    this.cartService.purchase(this.itemsInCart).subscribe(
      (res: any) => {
        this.cartService.emptyCart();
        this.itemsInCart = [];
        

        this.itemsPurchased = res;
        this.purchased=true;
        console.log(this.itemsPurchased);
      }
    );
  }

  calculateTotal() {
    this.total = this.itemsInCart.reduce((total, currVal) => total + (currVal.price * currVal.quantity + currVal.salesTax), 0)
  }

  onDecreaseQty(item: Item) {
    if (item.quantity > 0) {
      item.quantity--;
      this.calculateTotal();
    }
  }

  onIncreaseQty(item: Item) {
    if (item.quantity < item.available) {
      item.quantity++;
      this.calculateTotal();
    }
  }
}



