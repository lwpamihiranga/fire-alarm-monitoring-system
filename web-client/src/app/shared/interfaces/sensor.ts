// interface for the sensor data
export interface Sensor {
  isActive: boolean;
  location: Location;
  smokeLevel: number;
  co2Level: number;
}

interface Location {
  roomNo: number;
  floor: number;
}
