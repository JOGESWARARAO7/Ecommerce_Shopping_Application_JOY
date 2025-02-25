import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminheadderComponent } from './adminheadder.component';

describe('AdminheadderComponent', () => {
  let component: AdminheadderComponent;
  let fixture: ComponentFixture<AdminheadderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminheadderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminheadderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
