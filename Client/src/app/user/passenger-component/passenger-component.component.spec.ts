import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassengerComponentComponent } from './passenger-component.component';

describe('PassengerComponentComponent', () => {
  let component: PassengerComponentComponent;
  let fixture: ComponentFixture<PassengerComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PassengerComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PassengerComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
