import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KpiIntelligence } from './kpi-intelligence';

describe('KpiIntelligence', () => {
  let component: KpiIntelligence;
  let fixture: ComponentFixture<KpiIntelligence>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KpiIntelligence],
    }).compileComponents();

    fixture = TestBed.createComponent(KpiIntelligence);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
