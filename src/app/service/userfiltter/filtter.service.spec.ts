import { TestBed } from '@angular/core/testing';

import { FiltterService } from './filtter.service';

describe('FiltterService', () => {
  let service: FiltterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FiltterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
