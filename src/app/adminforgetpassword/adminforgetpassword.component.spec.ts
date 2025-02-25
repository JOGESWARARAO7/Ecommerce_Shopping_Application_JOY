import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminforgetpasswordComponent } from './adminforgetpassword.component';

describe('AdminforgetpasswordComponent', () => {
  let component: AdminforgetpasswordComponent;
  let fixture: ComponentFixture<AdminforgetpasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminforgetpasswordComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminforgetpasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
