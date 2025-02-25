import { TestBed } from '@angular/core/testing';

import { AdminforgetService } from './adminforget.service';

describe('AdminforgetService', () => {
  let service: AdminforgetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminforgetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
