import { inject } from '@angular/core';
import {Router,CanActivateFn, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

export const AuthGuard: CanActivateFn = (
 router:ActivatedRouteSnapshot,
 state: RouterStateSnapshot
):  Observable<boolean | UrlTree> 
| Promise<boolean | UrlTree> 
| boolean 
| UrlTree=> {

return inject(AuthService).authenticated()
  ? true
  : inject(Router).createUrlTree(['']);
};
  