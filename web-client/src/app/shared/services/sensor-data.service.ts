import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { timer, Observable, Subject, throwError, of } from 'rxjs';
import { switchMap, takeUntil, catchError, retry } from 'rxjs/operators';

import { Sensor } from '../interfaces/sensor';

@Injectable({
  providedIn: 'root',
})
export class SensorDataService {
  private url: string = 'http://localhost:3000/sensor/';

  constructor(private http: HttpClient) {}

  getSensorsData(): Observable<Sensor[]> {
    return this.http.get<Sensor[]>(this.url);
  }
}
