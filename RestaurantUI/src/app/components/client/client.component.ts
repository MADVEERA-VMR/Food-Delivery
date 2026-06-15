import { Component, inject, OnInit, signal, Signal } from '@angular/core';
import { Client } from '../../model/class/Client';
import { ClientService } from '../../services/client.service';
import { APIResponseModel } from '../../model/interface/roles';
import { FormsModule } from '@angular/forms';
import { AsyncPipe, DatePipe, JsonPipe, UpperCasePipe } from '@angular/common';
import { Observable } from 'rxjs';
import { AlertComponent } from '../../reusbleComponent/alert/alert.component';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [FormsModule,UpperCasePipe,DatePipe,JsonPipe,AsyncPipe,AlertComponent],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css'
})
export class ClientComponent implements OnInit {
  currentDate:Date = new Date();
  
  clientObj : Client = new Client();
  clientList : Client[] = [];

  clientService = inject(ClientService);

  userList$ : Observable<any> = new Observable<any>;

  firstName = signal("angular 18");

  ngOnInit(): void {
    const name = this.firstName();
    this.loadClients();
    this.userList$ = this.clientService.getAllUsers();
  }

  onFnSignal(){
    this.firstName.set("React JS");
  }

  loadClients(){
    this.clientService.getAllClients().subscribe((res:APIResponseModel)=>{
      this.clientList = res.data;
    })
  }

  OnSaveClient(){
    this.clientService.addUpdate(this.clientObj).subscribe((res:APIResponseModel)=>{
      if(res.result){
        this.loadClients();
      }else{
        alert(res.message);
      }
    })

  }

  onDelete(id: number){
    const isDelete = confirm("Are sure want to delete :"+id);
    if(isDelete){
      this.clientService.deleteByClientId(id).subscribe((res:APIResponseModel)=>{
        if(res.result){
          this.loadClients();
        }else{
          alert(res.message);
        }
      }) 

    }
    
  }

  onEdit(data: Client){
  
    this.clientObj = data;

  }

  resetClient(){
    this.clientObj = new Client();
  }

}
