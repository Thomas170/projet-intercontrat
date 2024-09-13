import {
  Component,
  EventEmitter,
  Input,
  Output,
  SimpleChanges,
} from '@angular/core';
import { Product } from '../../../models/product';

@Component({
  selector: 'app-cart-summary',
  standalone: true,
  imports: [],
  templateUrl: './cart-summary.component.html',
  styleUrl: './cart-summary.component.css',
})
export class CartSummaryComponent {
  @Input() productsPrice!: number; // will be based on a get request from database

  deliveryPrice: number = 199;
  totalPrice: number = 0; // sum products and delivery price

  ngOnInit() {
    this.calculateTotalPrice();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['productsPrice']) {
      this.totalPrice =
        changes['productsPrice'].currentValue + this.deliveryPrice;
    }
  }

  payOrder(): void {
    //TODO : call service
  }

  // Method to calculate total price
  calculateTotalPrice(): void {
    this.totalPrice = this.productsPrice + this.deliveryPrice;
  }
}
