import { TestBed } from '@angular/core/testing';

import { ProductremoveService } from './productremove.service';

describe('ProductremoveService', () => {
  let service: ProductremoveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductremoveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
