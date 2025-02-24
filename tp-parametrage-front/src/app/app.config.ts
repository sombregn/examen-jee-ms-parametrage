import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideToastr} from 'ngx-toastr';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptors} from '@angular/common/http';
import {httpInterceptorInterceptor} from './services/interceptor/http-interceptor.interceptor';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideToastr({
      progressBar: true,
      closeButton: true,
      newestOnTop: true,
      tapToDismiss: true,
      positionClass: 'toast-top-right',
      timeOut: 8000
    }),
    provideAnimationsAsync(),
    provideHttpClient(
      withInterceptors([httpInterceptorInterceptor])
    )
  ]
};
