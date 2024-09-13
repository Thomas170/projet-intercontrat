import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../../../models/product';
import { CartProductComponent } from './cart-product/cart-product.component';

@Component({
  selector: 'app-cart-products',
  standalone: true,
  imports: [CartProductComponent],
  templateUrl: './cart-products.component.html',
  styleUrl: './cart-products.component.css',
})
export class CartProductsComponent {
  @Input() products!: Product[];
  @Output() updateProduct: EventEmitter<Product> = new EventEmitter();

  updateProductParent(productUpdated: Product): void {
    this.updateProduct.emit(productUpdated);
  }
}
