import { Component } from '@angular/core';
import { ShopProductsComponent } from './shop-products/shop-products.component';

@Component({
  selector: 'app-shop',
  standalone: true,
  imports: [ShopProductsComponent],
  templateUrl: './shop.component.html',
  styleUrl: './shop.component.css'
})
export class ShopComponent {

}
