import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SensorStatusComponent } from './shared/components/sensor-status/sensor-status.component';
import { SensorListComponent } from './shared/components/sensor-list/sensor-list.component';

@NgModule({
  declarations: [AppComponent, SensorStatusComponent, SensorListComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
