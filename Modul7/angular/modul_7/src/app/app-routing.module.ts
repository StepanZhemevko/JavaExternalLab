import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{path:'',pathMatch:'full',redirectTo:'/coupons'},
{path:'coupons', loadChildren:()=> import('./my-module/my-module.module').then((m)=>m.MyModuleModule)}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
