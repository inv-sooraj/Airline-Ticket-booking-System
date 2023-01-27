import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelResListComponent } from './cancel-res-list.component';

describe('CancelResListComponent', () => {
  let component: CancelResListComponent;
  let fixture: ComponentFixture<CancelResListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancelResListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CancelResListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
