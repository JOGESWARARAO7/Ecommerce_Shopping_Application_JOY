import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddabagComponent } from './addabag.component';

describe('AddabagComponent', () => {
  let component: AddabagComponent;
  let fixture: ComponentFixture<AddabagComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddabagComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddabagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
