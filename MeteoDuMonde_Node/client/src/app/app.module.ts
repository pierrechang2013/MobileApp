import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { AuthComponent } from './auth/auth.component';
import { AuthInterceptor } from './auth/auth.interceptor';
import { CurrentComponent } from './current/current.component';
import { HoursComponent } from './hours/hours.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DataService } from './data.service'

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    CurrentComponent,
    HoursComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [DataService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,

      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
