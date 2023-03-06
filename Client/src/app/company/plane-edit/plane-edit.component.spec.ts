import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlaneEditComponent } from './plane-edit.component';

describe('PlaneEditComponent', () => {
  let component: PlaneEditComponent;
  let fixture: ComponentFixture<PlaneEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlaneEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlaneEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
