import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyComponentComponent } from './login.component';

describe('MyComponentComponent', () => {
  let component: MyComponentComponent;
  let fixture: ComponentFixture<MyComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MyComponentComponent]
    });
    fixture = TestBed.createComponent(MyComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
