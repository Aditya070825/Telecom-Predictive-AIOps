import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutiveMonitoring } from './executive-monitoring';

describe('ExecutiveMonitoring', () => {
  let component: ExecutiveMonitoring;
  let fixture: ComponentFixture<ExecutiveMonitoring>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecutiveMonitoring],
    }).compileComponents();

    fixture = TestBed.createComponent(ExecutiveMonitoring);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
