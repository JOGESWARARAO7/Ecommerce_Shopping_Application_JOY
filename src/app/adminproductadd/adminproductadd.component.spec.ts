import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminproductaddComponent } from './adminproductadd.component';

describe('AdminproductaddComponent', () => {
  let component: AdminproductaddComponent;
  let fixture: ComponentFixture<AdminproductaddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminproductaddComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminproductaddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
