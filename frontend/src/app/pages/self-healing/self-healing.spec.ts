import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelfHealing } from './self-healing';

describe('SelfHealing', () => {
  let component: SelfHealing;
  let fixture: ComponentFixture<SelfHealing>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelfHealing],
    }).compileComponents();

    fixture = TestBed.createComponent(SelfHealing);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
