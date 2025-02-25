import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckroductdetailsComponent } from './checkroductdetails.component';

describe('CheckroductdetailsComponent', () => {
  let component: CheckroductdetailsComponent;
  let fixture: ComponentFixture<CheckroductdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CheckroductdetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckroductdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
