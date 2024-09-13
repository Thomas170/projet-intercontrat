import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopProductsComponent } from './shop-products.component';

describe('ShopProductComponent', () => {
  let component: ShopProductsComponent;
  let fixture: ComponentFixture<ShopProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShopProductsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
