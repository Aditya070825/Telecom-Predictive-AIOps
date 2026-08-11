import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.scss'
})
export class Sidebar {
  constructor(private authService: AuthService) {}

  get role(): string | null {
    return this.authService.getRole();
  }

  canSee(page: string): boolean {
    const role = this.role;

    if (role === 'ROLE_A') {
      return page === 'overview' || page === 'incident-analytics';
    }
    if (role === 'ROLE_B') {
      return page !== 'executive-monitoring';
    }
    if (role === 'ROLE_C') {
      return page === 'executive-monitoring';
    }
    return false;
  }
}