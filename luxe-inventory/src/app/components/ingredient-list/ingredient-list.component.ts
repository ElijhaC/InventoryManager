import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { IngredientService } from '../../services/ingredient.service';
import { Ingredient } from '../../models/ingredient.model';
import { IngredientFormComponent } from '../ingredient-form/ingredient-form.component';

@Component({
  selector: 'app-ingredient-list',
  standalone: true,
  imports: [CommonModule, FormsModule, IngredientFormComponent],
  templateUrl: './ingredient-list.component.html'
})
export class IngredientListComponent implements OnInit {
  ingredients: Ingredient[] = [];

  constructor(private ingredientService: IngredientService) {}

  ngOnInit(): void {
    this.loadIngredients();
  }

  loadIngredients(): void {
    this.ingredientService.getAll().subscribe({
      next: (data) => this.ingredients = data,
      error: (err) => console.error('Failed to load ingredients', err)
    });
  }

  searchQuery: string = '';

searchIngredients(): void {
  if (this.searchQuery.trim()) {
    this.ingredientService.searchIngredients(this.searchQuery).subscribe(data => {
      this.ingredients = data;
    });
  } else {
    this.loadIngredients();
  }
}

clearSearch(): void {
  this.searchQuery = '';
  this.loadIngredients(); // reloads full list
}

  
  generateReport() {
    this.ingredientService.generateReport().subscribe(report => {
      console.log(report);
      alert(`Report generated: ${report.title} - ${report.generatedAt}`);
    });
  }
  deleteIngredient(id: number): void {
    if (confirm('Are you sure?')) {
      this.ingredientService.deleteIngredient(id).subscribe(() => this.loadIngredients());
    }
  }
}
