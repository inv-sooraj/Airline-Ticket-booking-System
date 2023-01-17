import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AirplaneRegComponent } from './airplane-reg.component';

describe('AirplaneRegComponent', () => {
  let component: AirplaneRegComponent;
  let fixture: ComponentFixture<AirplaneRegComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AirplaneRegComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AirplaneRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
