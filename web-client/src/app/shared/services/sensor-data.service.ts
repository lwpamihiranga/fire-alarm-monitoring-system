import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Sensor } from '../interfaces/sensor';

@Injectable({
  providedIn: 'root',
})
export class SensorDataService {
  // url to make the api call.
  private url: string = 'http://localhost:3000/sensor/';

  constructor(private http: HttpClient) {}

  // this method return and observer that user can subscribe to receive data
  getSensorsData(): Observable<Sensor[]> {
    return this.http.get<Sensor[]>(this.url);
  }
}
