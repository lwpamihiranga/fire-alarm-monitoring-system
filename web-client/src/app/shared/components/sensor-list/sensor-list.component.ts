import { Component, OnInit } from '@angular/core';
import { SensorDataService } from '../../services/sensor-data.service';
import { Sensor } from '../../interfaces/sensor';

@Component({
  selector: 'app-sensor-list',
  templateUrl: './sensor-list.component.html',
  styleUrls: ['./sensor-list.component.css'],
})
export class SensorListComponent implements OnInit {
  private sensors: Sensor[];

  constructor(private sensorDataService: SensorDataService) {
    this.sensors = [];
  }

  ngOnInit(): void {
    this.sensorDataService.getSensorsData().subscribe((data) => {
      this.sensors = data;
    });
  }
}
