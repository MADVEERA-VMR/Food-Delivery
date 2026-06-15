import { ApplicationModule, Component, OnInit, Signal, inject, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { APIResponseModel, ClientProject, Employee } from '../../model/interface/roles';
import { ClientService } from '../../services/client.service';
import { Client } from '../../model/class/Client';

@Component({
  selector: 'app-client-project',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './client-project.component.html',
  styleUrl: './client-project.component.css'
})
export class ClientProjectComponent implements OnInit{
 

  projectForm: FormGroup = new FormGroup({

    clientProjectId: new FormControl(0),
    projectName: new FormControl("",[Validators.required,Validators.minLength(4)]),
    startDate: new FormControl(""),
    expectedEndDate: new FormControl(""),
    leadByEmpId: new FormControl(""),
    completedDate: new FormControl(""),
    contactPerson: new FormControl(""),
    contactPersonContactNo: new FormControl(""),
    totalEmpWorking: new FormControl(""),
    projectCost: new FormControl(""),
    projectDetails: new FormControl(""),
    contactPersonEmailId: new FormControl(""),
    clientId: new FormControl("")

  })

  clientSer = inject(ClientService);
  empList: Employee[]=[];
  clientList: Client[]=[];

  projectList = signal<ClientProject[]>([]);
  
  ngOnInit(): void {
    this.loadClients();
    this.loadEmployees();
    this.getAllClientProjects();
  }

  getAllClientProjects(){
    this.clientSer.getAllClientsProjects().subscribe((res:APIResponseModel)=>{
      this.projectList.set(res.data);
    })
  }

  loadClients() {
      this.clientSer.getAllClients().subscribe((res:APIResponseModel)=>{
        this.clientList = res.data;
      })
    }

  loadEmployees(){
    this.clientSer.getAllEmployees().subscribe((res:APIResponseModel)=>{
    this.empList = res.data;
    })
  }

  onSaveProject(){
    const formValue = this.projectForm.value;
    debugger;
    this.clientSer.addClientProjectUpdate(formValue).subscribe((res:APIResponseModel)=>{
      if(res.result){
        alert("Project create SUCC")
      }else{
        alert("failed")
      }
    })
  }
  

}
