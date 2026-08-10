export type TowerStatus = 'ACTIVE' | 'DEGRADED' | 'MAINTENANCE' | 'DOWN';

export interface Tower {
  towerId: string;
  towerName: string;
  region: string;
  city: string;
  status: TowerStatus;
  signalStrengthDbm: number;
  connectedUsers: number;
  bandwidthUsageMbps: number;
  lastUpdated: string; // ISO datetime string from Spring's LocalDateTime
}