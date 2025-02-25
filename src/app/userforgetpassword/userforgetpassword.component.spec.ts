import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserforgetpasswordComponent } from './userforgetpassword.component';

describe('UserforgetpasswordComponent', () => {
  let component: UserforgetpasswordComponent;
  let fixture: ComponentFixture<UserforgetpasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserforgetpasswordComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserforgetpasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
