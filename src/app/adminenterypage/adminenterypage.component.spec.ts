import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminenterypageComponent } from './adminenterypage.component';

describe('AdminenterypageComponent', () => {
  let component: AdminenterypageComponent;
  let fixture: ComponentFixture<AdminenterypageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminenterypageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminenterypageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
