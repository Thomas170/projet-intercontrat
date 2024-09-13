import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Product } from '../models/product';
import mockProducts from './data.json';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiUrl = environment.apiUrl + '/products';

  mockdata: Product[] = mockProducts;

  constructor(private http: HttpClient) {}

  /*getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }*/

  getProducts(): Observable<Product[]> {
    return of(this.mockdata);
  }
}
