import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Product } from '../../../../models/product'; // Adjust the path to where your Product model is located

@Component({
  selector: 'app-cart-product',
  standalone: true,
  templateUrl: './cart-product.component.html',
  styleUrls: ['./cart-product.component.css'],
})
export class CartProductComponent {
  @Input() product!: Product; // Receives the product data from the parent (cart component)
  @Output() updateProduct: EventEmitter<Product> = new EventEmitter();

  get totalPrice(): number {
    return this.product.quantitySelected * this.product.prix;
  }
  // Increase product quantity in the cart
  increaseQuantity(): void {
    if (this.product.quantitySelected < this.product.stock) {
      this.product.quantitySelected++;
      console.log(this.product.quantitySelected);
      this.updateProduct.emit(this.product); // Notify parent about the quantity change
    }
  }

  // Decrease product quantity in the cart
  decreaseQuantity(): void {
    if (this.product.quantitySelected > 1) {
      this.product.quantitySelected--;
      this.updateProduct.emit(this.product); // Notify parent about the quantity change
    }
  }

  // Remove the product from the cart
  removeFromCart(): void {
    this.product.quantitySelected = 0;
    this.updateProduct.emit(this.product);
    // TODO this.removedFromCart.emit(this.cartProduct);  // Notify parent that this product has been removed
  }
}
