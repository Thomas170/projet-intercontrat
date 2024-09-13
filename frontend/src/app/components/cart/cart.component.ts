import { Component, signal, WritableSignal } from '@angular/core';
import { CartProductsComponent } from './cart-products/cart-products.component';
import { CartSummaryComponent } from './cart-summary/cart-summary.component';
import { Product } from '../../models/product';
import { ProductService } from '../../services/product.service';
import { tap } from 'rxjs';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CartProductsComponent, CartSummaryComponent],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css',
})
export class CartComponent {
  products: WritableSignal<Product[]> = signal([]);
  totalProductsPrice: number = 0;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService
      .getProducts()
      .pipe(
        tap((fetchProducts) => {
          console.log(fetchProducts);
          this.products.set(fetchProducts);
          this.computeTotalPrice();
        })
      )
      .subscribe();
  }

  computeTotalPrice(): void {
    this.totalProductsPrice = this.products().reduce(
      (total, product) => total + product.prix * product.quantitySelected,
      0
    );
  }

  updateProduct(productUpdated: Product): void {
    if (productUpdated.quantitySelected == 0) {
      // TODO : call delete
      this.products.set(
        this.products().filter((product) => product.id != productUpdated.id)
      );
      this.computeTotalPrice();
      return;
    }
    // TODO : call update
    this.products.update((products) =>
      products.map((product) =>
        product.id === productUpdated.id
          ? { ...product, quantitySelected: productUpdated.quantitySelected }
          : product
      )
    );
    this.computeTotalPrice();
  }
}
