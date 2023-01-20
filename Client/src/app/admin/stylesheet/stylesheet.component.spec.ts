import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StylesheetComponent } from './stylesheet.component';

describe('StylesheetComponent', () => {
  let component: StylesheetComponent;
  let fixture: ComponentFixture<StylesheetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StylesheetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StylesheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
