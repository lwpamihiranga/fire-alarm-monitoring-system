import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SensorListComponent } from './shared/components/sensor-list/sensor-list.component';
import { SensorComponent } from './shared/components/sensor/sensor.component';

@NgModule({
  declarations: [AppComponent, SensorListComponent, SensorComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
