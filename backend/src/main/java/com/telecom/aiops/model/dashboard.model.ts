export interface DashboardTowerRow {
  towerId: string;
  towerName: string;
  region: string;
  status: string;
  signalStrengthDbm: number;
}

export interface DashboardResponse {
  totalTowers: number;
  activeIncidents: number;
  avgNetworkHealthPercent: number;
  predictedRiskAlerts: number;
  towerStatusCounts: { [status: string]: number };
  towersNeedingAttention: DashboardTowerRow[];
}