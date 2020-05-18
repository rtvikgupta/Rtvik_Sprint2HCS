import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveappointmentComponent } from './approveappointment.component';

describe('ApproveappointmentComponent', () => {
  let component: ApproveappointmentComponent;
  let fixture: ComponentFixture<ApproveappointmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApproveappointmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveappointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
