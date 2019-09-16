import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemsCardComponent } from './items-master/items-card/items-card.component';
import { CartComponent } from './cart/cart.component';
import { ItemsMasterComponent } from './items-master/items-master.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AdminGuard } from './admin.guard';
import { AdminComponent } from './admin/admin.component';



const routes: Routes = [
  // {path: 'home', component:ItemsMasterComponent},
  { path: 'items', component: ItemsMasterComponent },
  { path: 'cart', component: CartComponent },
  {path: 'admin', component:AdminComponent},
  { path: '', redirectTo: 'items', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
