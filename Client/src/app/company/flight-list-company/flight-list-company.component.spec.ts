import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightListCompanyComponent } from './flight-list-company.component';

describe('FlightListCompanyComponent', () => {
  let component: FlightListCompanyComponent;
  let fixture: ComponentFixture<FlightListCompanyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlightListCompanyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FlightListCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
