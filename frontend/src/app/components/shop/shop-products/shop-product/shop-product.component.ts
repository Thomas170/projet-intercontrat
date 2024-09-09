import { Component, Input, Output } from '@angular/core';
import { Product } from '../../../../models/product';
import { EventEmitter } from 'stream';

@Component({
  selector: 'app-shop-product',
  standalone: true,
  imports: [],
  templateUrl: './shop-product.component.html',
  styleUrl: './shop-product.component.css'
})
export class ShopProductComponent {
  @Input() product!: Product;  // Receive product data from parent

  // Increase quantity
  increaseQuantity(): void {
    if (this.product.quantitySelected < this.product.stock) {
      this.product.quantitySelected++;
    }
  }

  // Decrease quantity
  decreaseQuantity(): void {
    if (this.product.quantitySelected > 1) {
      this.product.quantitySelected--;
    }
  }

  // Add product to cart
  addToCart(): void {
    //TODO : requete post
  }
}