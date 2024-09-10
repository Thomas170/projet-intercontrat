import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Product } from '../models/product';
import mockProducts from './data.json';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiUrl = 'https://api.example.com/products'; // Replace with your API endpoint

  mockdata: Product[] = mockProducts;

  constructor(private http: HttpClient) {}

  getProducts(): Observable<Product[]> {
    return of(this.mockdata);
    //return this.http.get<Product[]>(this.apiUrl)
  }
}
