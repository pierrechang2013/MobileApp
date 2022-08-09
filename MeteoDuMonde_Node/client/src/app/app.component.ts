import { DataService } from './data.service';
import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { WttrObject } from '../../../common/weather';
import { WeatherService } from './weather.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})




export class AppComponent {


  title = 'client';

  wo = new Array<WttrObject>();

  constructor(private ws: WeatherService, private ds: DataService) { }


  searchForm = new FormGroup({

    cityNames: new FormControl('')

  });

  onSubmit() {
    console.log(this.searchForm.value);
    //window.alert(this.searchForm.value.cityNames);
    // this.profileForm.reset();
    this.searchForm.setValue({
      cityNames: this.searchForm.value.cityNames
    })

    //console.log('99999999999');

    //测试赋值this.ws.data.subscribe({ next: (data: WttrObject[]) => this.wo =data});

    this.ws.getWeathers(this.searchForm.value.cityNames).subscribe((data: WttrObject[]) => {
      //console.log('8888888888');
      this.wo = JSON.parse(JSON.stringify(data));

      this.title = JSON.stringify(data);

      //公用服务
      //let a = this.ds.getData();
      //console.log('this.wo' + JSON.stringify(data));
      // this.profileForm.setValue({firstName:"tomtom"});
      this.ds.emitChange(this.wo);
    })
  }

  sendToHours() {
    this.ds.emitChange(this.wo);//

  }

  sendToCurrent() {
    this.ds.emitChange(this.wo);

  }



}
