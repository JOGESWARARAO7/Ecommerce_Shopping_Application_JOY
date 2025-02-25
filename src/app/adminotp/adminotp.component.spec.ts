import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminotpComponent } from './adminotp.component';

describe('AdminotpComponent', () => {
  let component: AdminotpComponent;
  let fixture: ComponentFixture<AdminotpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminotpComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminotpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
