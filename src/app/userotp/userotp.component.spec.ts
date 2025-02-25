import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserotpComponent } from './userotp.component';

describe('UserotpComponent', () => {
  let component: UserotpComponent;
  let fixture: ComponentFixture<UserotpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserotpComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserotpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
