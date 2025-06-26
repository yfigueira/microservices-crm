import {ApplicationConfig, inject, provideAppInitializer, provideZoneChangeDetection} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {AuthenticationService} from './services/authentication.service';
import {tokenInterceptor} from './interceptors/token/token.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAppInitializer(() => {
      const authService = inject(AuthenticationService);
      return authService.init();
    }),
    provideHttpClient(
      withInterceptors([
        tokenInterceptor
      ])
    )
  ]
};
