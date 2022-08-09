import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, map, Observable, tap  } from 'rxjs';
import { environment } from 'src/environments/environment';
import { WttrObject } from '../../../common/weather';


/*
* Service pour les opérations en lien avec la météo
*/
@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  //Conserve le sujet
  private _data?: BehaviorSubject<WttrObject[]>;

  constructor(private _httpClientModule: HttpClient) {
  }

  //Permet de retourner le sujet et de d'initialiser
  //le sujet à la ville par défaut si c'est la première requête
  //vous pouvez vous abonnez à data pour obtenir les données météos
  get data(): BehaviorSubject<WttrObject[]>{
    if(!this._data){
      this._data = new BehaviorSubject<WttrObject[]>([]);
      this.getWeathers();
    }
    return this._data;
  }



  //Lance une recherche météo pour une série de ville
  getWeathers(locations: string = environment.defaultLocation): Observable<WttrObject[]> {
    //console.log('请求到这里getWeathers',locations);
    //this._httpClientModule.get<WttrObject[]>(`${environment.apiBaseUrl}/weather?locations=${locations}`)
    //httpclient.get操作实际是获得了response的结果，返回的对象是一个包含了返回值的observable对象
    return this._httpClientModule.get<WttrObject[]>(`${environment.apiBaseUrl}/weather?locations=${locations}`)
    //On envoie également l'information dans le behaviorSubject
    //un component va pouvoir s'abonner et reçevoir l'information
    //this.data其实是一个BehaviorSubject对象，他发射了并保存了data,即httpclient的返回值。
    //所以上面的data()调用了这个方法，返回了已经存储数据的BehaviorSubject对象this.data
      .pipe(tap(data => this.data.next(data)));//BehaviorSubject发射并保存data，即httpclient返回的对象


  }
}
