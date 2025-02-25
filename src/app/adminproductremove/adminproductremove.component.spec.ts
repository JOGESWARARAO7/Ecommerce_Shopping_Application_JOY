import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminproductremoveComponent } from './adminproductremove.component';

describe('AdminproductremoveComponent', () => {
  let component: AdminproductremoveComponent;
  let fixture: ComponentFixture<AdminproductremoveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminproductremoveComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminproductremoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
