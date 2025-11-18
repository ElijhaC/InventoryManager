import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IngredientService } from '../../services/ingredient.service';
import { Ingredient } from '../../models/ingredient.model';

@Component({
  selector: 'app-ingredient-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ingredient-form.component.html'
})
export class IngredientFormComponent {
  @Output() refreshList = new EventEmitter<void>();
  ingredient: Ingredient = { name: '', category: '', quantity: 0, unit: '', reorderLevel: 0 };

  constructor(private ingredientService: IngredientService) {}

  addIngredient(): void {
    this.ingredientService.addIngredient(this.ingredient).subscribe({
      next: () => {
        this.refreshList.emit();
        alert('Ingredient added successfully!');
        this.ingredient = { name: '', category: '', quantity: 0, unit: '', reorderLevel: 0 };
      },
      error: err => {
        console.error('Error adding ingredient:', err);
        alert('Failed to add ingredient. Check your input.');
      }
    });
  }
}
