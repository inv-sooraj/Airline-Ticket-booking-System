import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelledReservationListComponent } from './cancelled-reservation-list.component';

describe('CancelledReservationListComponent', () => {
  let component: CancelledReservationListComponent;
  let fixture: ComponentFixture<CancelledReservationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancelledReservationListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CancelledReservationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
