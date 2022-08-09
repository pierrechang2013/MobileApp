import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AuthGuard } from './auth/auth.guard';
import { AuthComponent } from './auth/auth.component';
import { CurrentComponent } from './current/current.component';
import { HoursComponent } from './hours/hours.component';

const routes: Routes = [
  //On redirige / vers /weather
  {path:'',pathMatch:'full', redirectTo:'weather'},

  //canActivate: [AuthGuard] permet de bloquer
  //une route si l'utilisateur n'est pas login
  //TODO vous pouvez retirer temporairement le guard pour développer
  { path: 'weather', component: AppComponent, canActivate: [AuthGuard]},
  { path: 'current', component: CurrentComponent, canActivate: [AuthGuard]},
  { path: 'hours', component: HoursComponent, canActivate: [AuthGuard]},

  //Route pour la page d'authentification
  {path:'auth', component: AuthComponent },


  //TODO Ajouter les autres routes au besoin
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
