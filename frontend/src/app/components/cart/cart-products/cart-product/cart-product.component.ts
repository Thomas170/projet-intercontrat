import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Product } from '../../../../models/product'; // Adjust the path to where your Product model is located

@Component({
  selector: 'app-cart-product',
  standalone: true,
  templateUrl: './cart-product.component.html',
  styleUrls: ['./cart-product.component.css']
})
export class CartProductComponent {
  @Input() product!: Product;  // Receives the product data from the parent (cart component)
  //@Output() quantityChanged = new EventEmitter<Product>();  // Emits when the quantity is changed
  //@Output() removedFromCart = new EventEmitter<Product>();  // Emits when the product is removed from the cart

  // Increase product quantity in the cart
  increaseQuantity(): void {
    if (this.product.quantitySelected < this.product.stock) {
      this.product.quantitySelected++;
      //this.quantityChanged.emit(this.product);  // Notify parent about the quantity change
      //TODO :Update total
    }
  }

  // Decrease product quantity in the cart
  decreaseQuantity(): void {
    if (this.product.quantitySelected > 1) {
      this.product.quantitySelected--;
      //this.quantityChanged.emit(this.product);  // Notify parent about the quantity change
      // TODO : Update total
    }
  }

  // Remove the product from the cart
  removeFromCart(): void {
    // TODO this.removedFromCart.emit(this.cartProduct);  // Notify parent that this product has been removed
  }
}

