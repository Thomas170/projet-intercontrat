import { Component } from '@angular/core';

@Component({
  selector: 'app-cart-summary',
  standalone: true,
  imports: [],
  templateUrl: './cart-summary.component.html',
  styleUrl: './cart-summary.component.css',
})
export class CartSummaryComponent {
  productsPrice: number = 5; // will be based on a get request from database
  deliveryPrice: number = 5;
  totalPrice: number = 0; // sum products and delivery price

  ngOnInit() {
    this.calculateTotalPrice();
  }

  payOrder(): void {
    //TODO
  }

  // Method to calculate total price
  calculateTotalPrice(): void {
    this.totalPrice = this.productsPrice + this.deliveryPrice;
  }
}
