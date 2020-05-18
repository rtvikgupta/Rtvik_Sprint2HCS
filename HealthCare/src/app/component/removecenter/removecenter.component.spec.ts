import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemovecenterComponent } from './removecenter.component';

describe('RemovecenterComponent', () => {
  let component: RemovecenterComponent;
  let fixture: ComponentFixture<RemovecenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemovecenterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemovecenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
