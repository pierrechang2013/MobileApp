import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

/*
* Component pour faire l'authentification de vos utilisateurs
* Ce component sera affiché lorsque l'utilisateur n'est pas connecté
*/
@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  constructor(private _authService: AuthService, private _router: Router) { }

  ngOnInit(): void {
  }

  //Bouton login
  login(form: NgForm) {
    //这个this._authService.login(form.value)先执行，subscribe里面是观察者，
    //subscribe里面的观察者，订阅了this._authService.login(form.value),然后就获得了this._authService.login的返回值
    // this._authService.login的返回值来自于server 中auth.controller.ts里的的res.json();
    this._authService.login(form.value).subscribe({

      next: data => {

        console.log('data.token' + data.token);
        console.log('data.username' + data.username);
        console.log('data._id' + data._id);
        alert('Connexion reussit')


      },
      error: err => {
        //因为服务器那里我自己传403过来,所以直接到了错误这里
        alert('Erreur lors de la connexion,Valid,le nom ou le mot de passe n\'est pas corret,essaiez svp')
      }
    })
  }

  //Bouton signup
  signup(form: NgForm) {
    this._authService.signup(form.value).subscribe({
      error: err => alert("Erreur lors de l'inscription")
    })
  }

}
