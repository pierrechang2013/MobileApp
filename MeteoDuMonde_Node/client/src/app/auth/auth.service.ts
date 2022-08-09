import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Authentification } from '../../../../common/authentification';
import { UserDTO } from '../../../../common/user';

/*
* Service pour la gestion de l'authentification
*/
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private _httpClient: HttpClient, private _router: Router) { }

  //Contient les informations de l'utilisateur actif
  public user: BehaviorSubject<UserDTO> = new BehaviorSubject({username:''});

  //Permet à l'utilisateur de se connecter
  login(auth: Authentification): Observable<UserDTO>{



    return this._httpClient.post<UserDTO>(`${environment.apiBaseUrl}/auth/login`,auth)


    //On envoie l'utilisateur à tous les
    //aboonées du sujet user
      .pipe(tap(user => {//user是post以后返回的对象内容在这里是UserDTO，post他，其实，
                         //他其实是返回一个observable对象
                         //observable对象里包装了一个UserDTO对象
                         //this.user是 BehaviorSubject对象，里面包含了一个UserDTO
                         //post返回的observable对象被BehaviorSubject订阅，随着observable里的user(UserDTO)变化，
                         //并广播给了了BehaviorSubject，所以BehaviorSubject里的UserDTO也变化了。




      this.user.next(user)//保存并发射这个数据流

      //On dirige l'utilisateur vers /weather
      //this._router.navigate(['weather'])
      this._router.navigate(['current']);


    }));
  }

  //Permet à l'utilisateur de créer un compte
  signup(auth: Authentification): Observable<UserDTO>{
    return this._httpClient.post<UserDTO>(`${environment.apiBaseUrl}/auth/signup`,auth)
    //On envoie l'utilisateur à tous les
    //aboonées du sujet user
    .pipe(tap(user => {
      this.user.next(user)
      //On dirige l'utilisateur vers /weather
      //this._router.navigate(['weather'])
      this._router.navigate(['current']);
    }));
  }

  //Permet à l'utilisateur de se déconnecter
  logout():void{
    this.user.next({username:''});
    this._router.navigate(['auth']);
  }
}
