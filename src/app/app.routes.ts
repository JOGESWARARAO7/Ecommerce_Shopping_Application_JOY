import { Routes } from '@angular/router';
import { UserloginComponent } from './userlogin/userlogin.component';
import { ApplogoComponent } from './applogo/applogo.component';
import { UserhomepageComponent } from './userhomepage/userhomepage.component';
import { UsersignupComponent } from './usersignup/usersignup.component';
import { UserotpComponent } from './userotp/userotp.component';
import { ViewproductComponent } from './viewproduct/viewproduct.component';
import { WhishlistComponent } from './whishlist/whishlist.component';
import { FilterComponent } from './filter/filter.component';
import { AddabagComponent } from './addabag/addabag.component';
import { CheckroductdetailsComponent } from './checkroductdetails/checkroductdetails.component';
import { UserforgetpasswordComponent } from './userforgetpassword/userforgetpassword.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { AdminenterypageComponent } from './adminenterypage/adminenterypage.component';
import { AdminsingupComponent } from './adminsingup/adminsingup.component';
import { AdminforgetpasswordComponent } from './adminforgetpassword/adminforgetpassword.component';
import { AdminotpComponent } from './adminotp/adminotp.component';
import { AdminproductaddComponent } from './adminproductadd/adminproductadd.component';
import { AdminproductremoveComponent } from './adminproductremove/adminproductremove.component';

export const routes: Routes = [

    {
        path: '',
        redirectTo:'applogo',
        pathMatch:'full'  
    }
    ,
    {
        path:"applogo",
        component:ApplogoComponent

    },
    {
        path:"userloginpage",
        component:UserloginComponent

    },
    {
        path:"userhomepage",
        component:UserhomepageComponent
    },
    {
        path:"usersignup",
        component:UsersignupComponent
    },
    {
        path:"userotp",
        component:UserotpComponent
    },
    {
        path:"userhomepage",
        component:UserhomepageComponent
    },
    {
        path: "viewproduct/:productid",
        component: ViewproductComponent
        
    },
    {
        path:"whishlist",
        component:WhishlistComponent
    },
    {
        path:"filter",
        component:FilterComponent
    },
    {
        path:"addbag",
        component:AddabagComponent
    },
    {
        path:"checkout",
        component:CheckroductdetailsComponent
    },
    {
        path:"userforgetpassword",
        component:UserforgetpasswordComponent
    },
    {
        path:"adminenterypage",
        component:AdminenterypageComponent
    },
    {
        path:"adminloginpage",
        component:AdminloginComponent
    },
    {
        path:"adminsignuppage",
        component:AdminsingupComponent
    },
    {
        path:"adminforgetpassword",
        component:AdminforgetpasswordComponent
    },
    {
        path:"adminotp",
        component:AdminotpComponent
    },
    {
        path:"adminaddproduct",
        component:AdminproductaddComponent
    },
    {
        path:"adminproductremove",
        component:AdminproductremoveComponent
    }

];
