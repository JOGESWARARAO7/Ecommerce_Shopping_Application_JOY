import { TestBed } from '@angular/core/testing';

import { UserforgetService } from './userforget.service';

describe('UserforgetService', () => {
  let service: UserforgetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserforgetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
