import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TowerService } from '../../services/tower.service';
import { Tower } from '../../models/tower.model';

@Component({
  selector: 'app-overview',
  imports: [CommonModule],
  templateUrl: './overview.html',
  styleUrl: './overview.scss',
})
export class Overview implements OnInit {
  towers = signal<Tower[]>([]);
  loading = signal(true);
  error = signal(false);

  constructor(private towerService: TowerService) {}

  ngOnInit(): void {
    this.towerService.getTowers().subscribe({
      next: (data) => {
        this.towers.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        console.error('Failed to load towers', err);
        this.error.set(true);
        this.loading.set(false);
      }
    });
  }
}
