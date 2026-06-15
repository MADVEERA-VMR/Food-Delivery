import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../model/class/Client';
import { APIResponseModel } from '../model/interface/roles';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { 

   }

   getAllUsers(){
    return this.http.get("https://jsonplaceholder.typicode.com/users")
   }

   getAllClients():Observable<APIResponseModel> {
    return this.http.get<APIResponseModel>("http://localhost:8080/myapp/clients/all")
   }

   getAllClientsProjects():Observable<APIResponseModel> {
    return this.http.get<APIResponseModel>("http://localhost:8080/myapp/clientprojects/all")
   }

   addUpdate(obj:Client):Observable<APIResponseModel> {
    return this.http.post<APIResponseModel>("http://localhost:8080/myapp/clients/add",obj)
   }

   deleteByClientId(id: number):Observable<APIResponseModel> {
    return this.http.delete<APIResponseModel>("http://localhost:8080/myapp/clients/"+id)
   }

   getAllEmployees():Observable<APIResponseModel> {
    return this.http.get<APIResponseModel>("http://localhost:8080/myapp/employees/all")
   }

   addClientProjectUpdate(obj: Client):Observable<APIResponseModel>{
    return this.http.post<APIResponseModel>("http://localhost:8080/myapp/clientprojects/add",obj)
   }

}
