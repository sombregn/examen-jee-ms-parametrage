/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { RegistrationResponse } from '../../models/registration-response';

export interface GetRegistrationById$Params {
  id: number;
}

export function getRegistrationById(http: HttpClient, rootUrl: string, params: GetRegistrationById$Params, context?: HttpContext): Observable<StrictHttpResponse<RegistrationResponse>> {
  const rb = new RequestBuilder(rootUrl, getRegistrationById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<RegistrationResponse>;
    })
  );
}

getRegistrationById.PATH = '/registrations/{id}';
