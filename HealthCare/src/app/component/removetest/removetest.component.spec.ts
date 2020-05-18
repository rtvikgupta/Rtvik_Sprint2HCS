import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemovetestComponent } from './removetest.component';

describe('RemovetestComponent', () => {
  let component: RemovetestComponent;
  let fixture: ComponentFixture<RemovetestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemovetestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemovetestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
