import { Component, OnInit, Input } from '@angular/core';
import { Item } from '../../item';
import { CartService } from 'src/app/cart.service';

@Component({
  selector: 'app-items-card',
  templateUrl: './items-card.component.html',
  styleUrls: ['./items-card.component.scss']
})
export class ItemsCardComponent implements OnInit {
  @Input() item: Item;
  buttonText = "Add to Cart";
  qtyToPurchase = 1;

  constructor(private cartService: CartService) { }

  ngOnInit() {
  }

  onAddToCart(item: Item) {
    console.log(item.quantity);
    if (this.qtyToPurchase > 0 && this.qtyToPurchase <= item.quantity) {
      this.cartService.addToCart(item, this.qtyToPurchase);
      this.buttonText = "Added";
  
      setTimeout(() => {
        this.buttonText = "Add to Cart";
      }, 1500);
    }
  }

}
