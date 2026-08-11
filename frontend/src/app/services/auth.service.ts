import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

export interface CurrentUser {
  username: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private credentials: string | null = null;
  private currentUser: CurrentUser | null = null;

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<CurrentUser> {
    const encoded = btoa(`${username}:${password}`);
    const headers = { Authorization: `Basic ${encoded}` };

    return this.http.get<CurrentUser>('/api/auth/me', { headers }).pipe(
      tap(user => {
        this.credentials = encoded;
        this.currentUser = user;
      })
    );
  }

  logout(): void {
    this.credentials = null;
    this.currentUser = null;
  }

  isLoggedIn(): boolean {
    return this.credentials !== null;
  }

  getAuthHeader(): string | null {
    return this.credentials ? `Basic ${this.credentials}` : null;
  }

  getRole(): string | null {
    return this.currentUser?.role ?? null;
  }

  getUsername(): string | null {
    return this.currentUser?.username ?? null;
  }
}