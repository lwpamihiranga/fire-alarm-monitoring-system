import { Component, OnInit, Input } from '@angular/core';
import { Sensor } from '../../interfaces/sensor';

@Component({
  selector: 'app-sensor',
  templateUrl: './sensor.component.html',
  styleUrls: ['./sensor.component.css'],
})
export class SensorComponent implements OnInit {
  // get the input parameter passed to the component from the parent component
  @Input() sensor: Sensor;

  constructor() {}

  ngOnInit(): void {}

  // check whether a passed sensor is active or not
  checkActive(sensor: Sensor) {
    return sensor.isActive;
  }

  // this function checks for whether a given sensor's smoke level or co2 level is greter than 5. It will return true if the level is
  // greater than 5, otherwise false.
  isDanger(sensor: Sensor) {
    if (sensor.smokeLevel > 5 || sensor.co2Level > 5) {
      return true;
    } else {
      return false;
    }
  }
}
