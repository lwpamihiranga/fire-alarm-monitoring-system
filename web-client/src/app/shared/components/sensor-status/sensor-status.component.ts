import { Component, OnInit } from '@angular/core';
import { SensorDataService } from '../../services/sensor-data.service';
import { Sensor } from '../../interfaces/sensor';

@Component({
  selector: 'app-sensor-status',
  templateUrl: './sensor-status.component.html',
  styleUrls: ['./sensor-status.component.css'],
})
export class SensorStatusComponent implements OnInit {
  sensor: Sensor[];
  constructor(private sensorDataService: SensorDataService) {}

  ngOnInit(): void {
    this.sensorDataService.getSensorsData().subscribe((data) => {
      this.sensor = data;
    });
  }

  printData() {
    console.log(this.sensor);
  }
}
