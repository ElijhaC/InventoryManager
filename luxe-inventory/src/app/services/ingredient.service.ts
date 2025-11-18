import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ingredient } from '../models/ingredient.model';

@Injectable({ providedIn: 'root' })
export class IngredientService {
  private baseUrl = 'http://localhost:8080/api/ingredients';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(`${this.baseUrl}/all`);
  }

  addIngredient(ingredient: Ingredient): Observable<Ingredient> {
    return this.http.post<Ingredient>(`${this.baseUrl}/add`, ingredient);
  }

  deleteIngredient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

  searchIngredients(query: string): Observable<Ingredient[]> {
  return this.http.get<Ingredient[]>(`${this.baseUrl}/search?query=${query}`);
  }

 generateReport(): Observable<any> {
   return this.http.get(`${this.baseUrl}/report`);
 }

}
