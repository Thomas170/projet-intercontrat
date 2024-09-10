import { Routes } from '@angular/router';
import { ShopComponent } from './components/shop/shop.component';
import { CartComponent } from './components/cart/cart.component';

export const routes: Routes = [
    { path: 'shop', component: ShopComponent },
    { path: 'cart', component: CartComponent },
];
