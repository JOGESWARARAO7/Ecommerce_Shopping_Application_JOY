import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplogoComponent } from './applogo.component';

describe('ApplogoComponent', () => {
  let component: ApplogoComponent;
  let fixture: ComponentFixture<ApplogoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApplogoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApplogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
