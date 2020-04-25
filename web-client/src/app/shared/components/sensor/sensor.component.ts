import { Component, OnInit, Input } from '@angular/core';
import { Sensor } from '../../interfaces/sensor';

@Component({
  selector: 'app-sensor',
  templateUrl: './sensor.component.html',
  styleUrls: ['./sensor.component.css'],
})
export class SensorComponent implements OnInit {
  @Input() sensor: Sensor;

  constructor() {
  }

  ngOnInit(): void { }

  checkActive(sensor: Sensor) {
    return sensor.isActive;
  }

  isDanger(sensor: Sensor) {
    if (sensor.smokeLevel >= 5 || sensor.co2Level >= 5) {
      return true;
    } else {
      return false;
    }
  }
}
