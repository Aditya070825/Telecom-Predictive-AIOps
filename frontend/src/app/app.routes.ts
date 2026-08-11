import { Routes } from '@angular/router';
import { Overview } from './pages/overview/overview';
import { KpiIntelligence } from './pages/kpi-intelligence/kpi-intelligence';
import { IncidentAnalytics } from './pages/incident-analytics/incident-analytics';
import { RiskPrediction } from './pages/risk-prediction/risk-prediction';
import { Forecasting } from './pages/forecasting/forecasting';
import { SelfHealing } from './pages/self-healing/self-healing';
import { ExecutiveMonitoring } from './pages/executive-monitoring/executive-monitoring';
import { Login } from './pages/login/login';
import { authGuard } from './services/auth.guard';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: '', redirectTo: 'overview', pathMatch: 'full' },
  { path: 'overview', component: Overview, canActivate: [authGuard] },
  { path: 'kpi-intelligence', component: KpiIntelligence, canActivate: [authGuard] },
  { path: 'incident-analytics', component: IncidentAnalytics, canActivate: [authGuard] },
  { path: 'risk-prediction', component: RiskPrediction, canActivate: [authGuard] },
  { path: 'forecasting', component: Forecasting, canActivate: [authGuard] },
  { path: 'self-healing', component: SelfHealing, canActivate: [authGuard] },
  { path: 'executive-monitoring', component: ExecutiveMonitoring, canActivate: [authGuard] },
  { path: '**', redirectTo: 'overview' }
];