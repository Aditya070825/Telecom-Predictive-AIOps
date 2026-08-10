import { Routes } from '@angular/router';
import { Overview } from './pages/overview/overview';
import { KpiIntelligence } from './pages/kpi-intelligence/kpi-intelligence';
import { IncidentAnalytics } from './pages/incident-analytics/incident-analytics';
import { RiskPrediction } from './pages/risk-prediction/risk-prediction';
import { Forecasting } from './pages/forecasting/forecasting';
import { SelfHealing } from './pages/self-healing/self-healing';
import { ExecutiveMonitoring } from './pages/executive-monitoring/executive-monitoring';

export const routes: Routes = [
  { path: '', redirectTo: 'overview', pathMatch: 'full' },
  { path: 'overview', component: Overview },
  { path: 'kpi-intelligence', component: KpiIntelligence },
  { path: 'incident-analytics', component: IncidentAnalytics },
  { path: 'risk-prediction', component: RiskPrediction },
  { path: 'forecasting', component: Forecasting },
  { path: 'self-healing', component: SelfHealing },
  { path: 'executive-monitoring', component: ExecutiveMonitoring },
  { path: '**', redirectTo: 'overview' }
];