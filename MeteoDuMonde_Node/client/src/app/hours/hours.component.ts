import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { WttrObject } from '../../../../common/weather';
import { WeatherService } from '../weather.service';
import { DataService } from '../data.service';


@Component({
  selector: 'app-hours',
  templateUrl: './hours.component.html',
  styleUrls: ['./hours.component.css'],
  //inputs: ['wo']
})
export class HoursComponent implements OnInit {

  // @Output() private outer = new EventEmitter();

   wo = new Array<WttrObject>();//加入input注解

  constructor(private ws: WeatherService, private ds: DataService) {

    this.ds.changeEmitted$.subscribe(
     meteo => {
        console.log('hours接收到的' + meteo);
        this.wo = meteo;
      });

   }

  ngOnInit(): void {



      //this.ws.getWeathers().subscribe((data: WttrObject[]) => { this.wo = JSON.parse(JSON.stringify(data));  console.log('this.wo' + JSON.stringify(data));
  //});

  }


  // sendToApp() {

  //   this.outer.emit('我来自hours');


  // }

}
