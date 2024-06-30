import {Inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class BaseService<T, U, V> {
  protected entityName: string;
  constructor(private httpClient: HttpClient) {

  }
  
  getAllObjects() {
    return this.httpClient.get<T[]>(`${environment.baseUrl}/${this.entityName}`);
  }
  
  getObjectById(id: string) {
    return this.httpClient.get<U>(`${environment.baseUrl}/${this.entityName}/${id}`);
  }
  
  createObject(object: V) {
    return this.httpClient.post<V>(`${environment.baseUrl}/${this.entityName}`, object);
  }
  
  updateObject(id: string, object: V) {
    return this.httpClient.put<V>(`${environment.baseUrl}/${this.entityName}/${id}`, object);
  }
}
