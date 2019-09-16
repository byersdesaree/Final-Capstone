import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CartComponent } from './cart/cart.component';
import { ItemsMasterComponent } from './items-master/items-master.component';
import { ItemsCardComponent } from './items-master/items-card/items-card.component';
import { FooterComponent } from './footer/footer.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ItemSearchPipe } from './item-search.pipe';
import {ShoppingCartModule} from 'ng-shopping-cart';
import { AdminComponent } from './admin/admin.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CartComponent,
    ItemsMasterComponent,
    ItemsCardComponent,
    FooterComponent,
    PageNotFoundComponent,
    ItemSearchPipe,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ShoppingCartModule.forRoot({
      serviceType: 'localStorage',
      serviceOptions: {
        storageKey: 'NgShoppingCart',
        clearOnError: true
      }
    }), 
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
