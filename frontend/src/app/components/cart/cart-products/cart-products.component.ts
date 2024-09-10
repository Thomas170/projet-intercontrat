import { Component, Input } from '@angular/core';
import { Product } from '../../../models/product';
import { CartProductComponent } from './cart-product/cart-product.component';
import mockProducts from './data.json';

@Component({
  selector: 'app-cart-products',
  standalone: true,
  imports: [CartProductComponent],
  templateUrl: './cart-products.component.html',
  styleUrl: './cart-products.component.css'
})
export class CartProductsComponent {
  products : Product[] = mockProducts;
}
