import { WeatherService } from '../weather.service';
import { Component, OnInit,Input } from '@angular/core';
import { CurrentCondition} from '../../../../common/weather'
import { WttrObject } from '../../../../common/weather'
import { DataService } from '../data.service';

@Component({
  selector: 'app-current',
  templateUrl: './current.component.html',
  styleUrls: ['./current.component.css']
})
export class CurrentComponent implements OnInit {

  @Input() wo = new Array<WttrObject>();

  constructor(private ws: WeatherService, private ds: DataService) {

    this.ds.changeEmitted$.subscribe(
      meteo => {
        console.log('CurrentComponent接收到的' + meteo);
        this.wo = meteo;
      });
  }

  ngOnInit(): void {
    //this.ws.getWeathers().subscribe((data: WttrObject[]) => { this.wo = JSON.parse(JSON.stringify(data))})

    //this.ws.getWeathers().subscribe((data: WttrObject[]) => { this.wo = JSON.parse(JSON.stringify(data)); //console.log('this.wo' + JSON.stringify(data));
  //});

  }

}

