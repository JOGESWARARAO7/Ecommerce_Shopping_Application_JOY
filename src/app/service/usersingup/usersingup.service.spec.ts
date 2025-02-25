import { TestBed } from '@angular/core/testing';

import { UsersingupService } from './usersingup.service';

describe('UsersingupService', () => {
  let service: UsersingupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsersingupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
