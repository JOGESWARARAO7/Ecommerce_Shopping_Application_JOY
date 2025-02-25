import { TestBed } from '@angular/core/testing';

import { AddbagService } from './addbag.service';

describe('AddbagService', () => {
  let service: AddbagService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddbagService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
