import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminproductmodifyComponent } from './adminproductmodify.component';

describe('AdminproductmodifyComponent', () => {
  let component: AdminproductmodifyComponent;
  let fixture: ComponentFixture<AdminproductmodifyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminproductmodifyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminproductmodifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
