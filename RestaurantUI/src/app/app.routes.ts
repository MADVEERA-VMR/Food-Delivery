import { Routes } from '@angular/router';
import { MasterComponent } from './components/master/master.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { ClientComponent } from './components/client/client.component';
import { ClientProjectComponent } from './components/client-project/client-project.component';
import { RolesComponent } from './components/roles/roles.component';
import { RestaurantListingComponent } from './components/restaurant-listing/restaurant-listing.component';
import { FoodCatalogueComponent } from './components/food-catalogue/food-catalogue.component';
import { OrderSummaryComponent } from './components/order-summary/order-summary.component';

export const routes: Routes = [{
    path:'',
    redirectTo: 'restaurant-listing',
    pathMatch: 'full'
},
{
    path:'master',
    component:MasterComponent
},
{
    path:'employee',
    component:EmployeeComponent
},
{
    path:'client',
    component:ClientComponent
},
{
    path:'client-project',
    component:ClientProjectComponent
},
{
    path:'restaurant-listing',
    component:RestaurantListingComponent
},
{
    path:'food-catalogue/:id',
    component:FoodCatalogueComponent
},
{
    path:'orderSummary',
    component:OrderSummaryComponent
}
];
