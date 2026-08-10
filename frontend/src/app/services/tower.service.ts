import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tower } from '../models/tower.model';

@Injectable({
  providedIn: 'root'
})
export class TowerService {
  private readonly apiUrl = '/api/towers';

  constructor(private http: HttpClient) {}

  getTowers(): Observable<Tower[]> {
    return this.http.get<Tower[]>(this.apiUrl);
  }
}
