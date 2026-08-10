import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncidentAnalytics } from './incident-analytics';

describe('IncidentAnalytics', () => {
  let component: IncidentAnalytics;
  let fixture: ComponentFixture<IncidentAnalytics>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncidentAnalytics],
    }).compileComponents();

    fixture = TestBed.createComponent(IncidentAnalytics);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
