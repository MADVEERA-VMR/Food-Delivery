import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { APIResponseModel } from '../model/interface/roles';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class MasterService {

  constructor(private http: HttpClient) { }

  getDesignations(): Observable<APIResponseModel>{
    return this.http.get<APIResponseModel>("http://localhost:8080/myapp/designation/all")
  }
}
