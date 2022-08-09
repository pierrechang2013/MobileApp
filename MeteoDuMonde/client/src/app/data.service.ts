import { Injectable, EventEmitter } from "@angular/core";
import { Subject } from 'rxjs';
import { Observable } from 'rxjs';



@Injectable()
export class DataService {
  private emitChangeSource = new Subject<any>();
  // Observable string streams
  changeEmitted$ = this.emitChangeSource.asObservable();
  // Service message commands
  emitChange(change: any) {
    this.emitChangeSource.next(change);
  }
}
