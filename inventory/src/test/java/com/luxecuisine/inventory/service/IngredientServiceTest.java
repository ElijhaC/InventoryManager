package com.luxecuisine.inventory.service;

import com.luxecuisine.inventory.model.Ingredient;
import com.luxecuisine.inventory.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    private Ingredient sugar;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        sugar = new Ingredient("Sugar", "Baking", 10.0, "kg", 2.0);
    }

    @Test
    void addIngredient_ShouldSaveAndReturnIngredient() {
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(sugar);

        Ingredient saved = ingredientService.addIngredient(sugar);

        assertThat(saved.getName()).isEqualTo("Sugar");
        verify(ingredientRepository, times(1)).save(sugar);
    }

    @Test
    void getAllIngredients_ShouldReturnList() {
        when(ingredientRepository.findAll()).thenReturn(List.of(sugar));

        List<Ingredient> result = ingredientService.getAllIngredients();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCategory()).isEqualTo("Baking");
    }

    @Test
    void updateIngredient_ShouldModifyExisting() {
        Ingredient updated = new Ingredient("Brown Sugar", "Baking", 8.0, "kg", 2.0);
        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(sugar));
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(updated);

        Ingredient result = ingredientService.updateIngredient(1L, updated);

        assertThat(result.getName()).isEqualTo("Brown Sugar");
    }

    @Test
    void searchIngredients_ShouldReturnFilteredResults() {
        when(ingredientRepository.findByNameContainingIgnoreCase("sug"))
                .thenReturn(List.of(sugar));

        List<Ingredient> found = ingredientService.searchIngredients("sug");

        assertThat(found).extracting("name").contains("Sugar");
    }

    @Test
    void generateReport_ShouldContainTitleAndTimestamp() {
        when(ingredientRepository.findAll()).thenReturn(List.of(sugar));
        when(ingredientRepository.count()).thenReturn(1L);

        Map<String, Object> report = ingredientService.generateReport();

        assertThat(report).containsKeys("title", "generatedAt", "data", "totalItems");
    }
}
