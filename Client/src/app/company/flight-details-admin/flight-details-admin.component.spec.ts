import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightDetailsAdminComponent } from './flight-details-admin.component';

describe('FlightDetailsAdminComponent', () => {
  let component: FlightDetailsAdminComponent;
  let fixture: ComponentFixture<FlightDetailsAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlightDetailsAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FlightDetailsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
