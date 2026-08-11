import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
  username = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.errorMessage = '';

    this.authService.login(this.username, this.password).subscribe({
      next: (user) => {
        this.redirectByRole(user.role);
      },
      error: () => {
        this.errorMessage = 'Invalid username or password.';
      }
    });
  }

  private redirectByRole(role: string): void {
    if (role === 'ROLE_C') {
      this.router.navigate(['/executive-monitoring']);
    } else {
      this.router.navigate(['/overview']);
    }
  }
}