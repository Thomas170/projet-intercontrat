import { Component } from '@angular/core';
import { Product } from '../../../models/product';
import { ShopProductComponent } from './shop-product/shop-product.component';
import mockProducts from './data.json';

@Component({
  selector: 'app-shop-products',
  standalone: true,
  imports: [ShopProductComponent],
  templateUrl: './shop-products.component.html',
  styleUrl: './shop-products.component.css',
})
export class ShopProductsComponent {
  products: Product[] = [];

  constructor() {
    // Mock Data
    this.products = mockProducts;
  }

  ngOnInit(): void {
    //this.productService.getProducts();
  }
}
