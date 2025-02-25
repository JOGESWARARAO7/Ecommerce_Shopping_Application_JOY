import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserheadderComponent } from './userheadder.component';

describe('UserheadderComponent', () => {
  let component: UserheadderComponent;
  let fixture: ComponentFixture<UserheadderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserheadderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserheadderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
