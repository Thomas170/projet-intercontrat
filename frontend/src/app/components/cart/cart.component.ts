import { Component } from '@angular/core';
import { CartProductsComponent } from './cart-products/cart-products.component';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CartProductsComponent],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

}
