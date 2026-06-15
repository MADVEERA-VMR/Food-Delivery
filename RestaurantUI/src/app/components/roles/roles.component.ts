import { Component,inject,OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IRole } from '../../model/interface/roles';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-roles',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './roles.component.html',
  styleUrl: './roles.component.css'
})
export class RolesComponent implements OnInit{
    
    rolesList: IRole [] = [];  
    http = inject(HttpClient);
    roleLoader : boolean = true;

    ngOnInit(): void {
      this.getAllRoles();
      this.roleLoader = false;
    }

    getAllRoles(){
      this.http.get("http://localhost:8080/myapp/roles/all").subscribe((result:any)=>{
          this.rolesList = result;
      })
    }

}
