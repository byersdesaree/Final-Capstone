import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { CartService } from 'src/app/cart.service';
import { AdminService } from '../admin.service';
import {Item} from '../item';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit, OnDestroy {
  cartItems: Item[] = [];
  loginForm = this.fb.group({
    username: [''],
    password: ['']
  });
  loginBeingEdited: string;
  getSub: Subscription;
  constructor(private cartService: CartService, private adminService: AdminService, private fb: FormBuilder) { }

  ngOnInit() {
    this.onSubmitForm();
    // this.getCartItems();
  }

  ngOnDestroy() {
    if (this.getSub) {
      this.getSub.unsubscribe();
    }
  }
  
  getCartItems() {
    this.cartItems=this.cartService.getCartItems();
  }
  onSubmitForm() {
    if (this.adminService.isAuthenticated == undefined) {
      this.loginForm.reset();
    } else {
      this.getCartItems();
    }
  }


}
