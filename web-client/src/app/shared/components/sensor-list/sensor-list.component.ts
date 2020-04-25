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
  // property to store the sensor data that receives from the api
  sensors: Sensor[];

  // injecting the sensorDataService to the component
  constructor(private sensorDataService: SensorDataService) {}

  ngOnInit(): void {}

  private killTrigger: Subject<void> = new Subject();

  // create an observer to call the fetch request on data service
  private fetchData: Observable<
    Sensor[]
  > = this.sensorDataService.getSensorsData();

  // here Rxjs is used to call the api request periodically. this will run at the start and then after every 40 seconds.
  // timer(0, 40000) represents it. interval time is given in miliseconds.
  private refrestInterval = timer(0, 40000)
    .pipe(
      takeUntil(this.killTrigger),
      switchMap(() => this.fetchData)
    )
    //subscribing to the observer to receive sensor data
    .subscribe((data) => {
      this.sensors = data;
    });

  // destroy the observer in case of closing the component. this is used to reduce memory leak
  ngOnDestroy() {
    this.killTrigger.next();
  }
}
