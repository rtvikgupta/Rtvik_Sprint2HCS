import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TakeappointmentComponent } from './takeappointment.component';

describe('TakeappointmentComponent', () => {
  let component: TakeappointmentComponent;
  let fixture: ComponentFixture<TakeappointmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TakeappointmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TakeappointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
