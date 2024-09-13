import { Component } from '@angular/core';
import { ShopProductsComponent } from './shop-products/shop-products.component';
import { ProductService } from '../../services/product.service';
import { tap } from 'rxjs';
import { Product } from '../../models/product';

@Component({
  selector: 'app-shop',
  standalone: true,
  imports: [ShopProductsComponent],
  templateUrl: './shop.component.html',
  styleUrl: './shop.component.css',
})
export class ShopComponent {
  products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.productService
      .getProducts()
      .pipe(
        tap((products) => {
          this.products = products;
        })
      )
      .subscribe();
  }
}
