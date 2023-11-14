import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlacingComponent } from './placing.component';

describe('PlacingComponent', () => {
  let component: PlacingComponent;
  let fixture: ComponentFixture<PlacingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PlacingComponent]
    });
    fixture = TestBed.createComponent(PlacingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
