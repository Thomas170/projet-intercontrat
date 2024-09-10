import { Component } from '@angular/core';
import { Product } from '../../../models/product';
import { ShopProductComponent } from './shop-product/shop-product.component';
import { ProductService } from '../../../services/product.service';

@Component({
  selector: 'app-shop-products',
  standalone: true,
  imports: [ShopProductComponent],
  templateUrl: './shop-products.component.html',
  styleUrl: './shop-products.component.css',
})
export class ShopProductsComponent {
  products: Product[] = [];

  constructor(private productService: ProductService) {
    // Mock Data
    this.products;
  }

  ngOnInit(): void {
    this.productService.getProducts();
  }
}
