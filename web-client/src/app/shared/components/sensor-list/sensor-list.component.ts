import { Component, OnInit } from '@angular/core';
import { SensorDataService } from '../../services/sensor-data.service';
import { Sensor } from '../../interfaces/sensor';
import { Observable, timer, Subject } from 'rxjs';
import { takeUntil, switchMap, catchError } from 'rxjs/operators';
@Component({
  selector: 'app-sensor-list',
  templateUrl: './sensor-list.component.html',
  styleUrls: ['./sensor-list.component.css'],
})
export class SensorListComponent implements OnInit {
  sensors: Sensor[];

  constructor(private sensorDataService: SensorDataService) {}

  ngOnInit(): void {}

  private killTrigger: Subject<void> = new Subject();

  private fetchData: Observable<
    Sensor[]
  > = this.sensorDataService.getSensorsData();

  private refrestInterval = timer(0, 40000)
    .pipe(
      takeUntil(this.killTrigger),
      switchMap(() => this.fetchData)
      // catchError(error => 'Error')
    )
    .subscribe((data) => {
      this.sensors = data;
    });

  ngOnDestroy() {
    this.killTrigger.next();
  }
}
