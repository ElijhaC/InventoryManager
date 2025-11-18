package com.luxecuisine.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import com.luxecuisine.inventory.model.Ingredient;
import com.luxecuisine.inventory.repository.IngredientRepository;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient newData) {
        return ingredientRepository.findById(id).map(i -> {
            i.setName(newData.getName());
            i.setCategory(newData.getCategory());
            i.setQuantity(newData.getQuantity());
            i.setUnit(newData.getUnit());
            i.setReorderLevel(newData.getReorderLevel());
            return ingredientRepository.save(i);
        }).orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    public List<Ingredient> searchIngredients(String name) {
        return ingredientRepository.findByNameContainingIgnoreCase(name);
    }

    public Map<String, Object> generateReport() {
        Map<String, Object> report = new LinkedHashMap<>();
        report.put("title", "LuxeCuisine Inventory Report");
        report.put("generatedAt", LocalDateTime.now());
        report.put("totalItems", ingredientRepository.count());
        report.put("data", ingredientRepository.findAll());
        return report;
    }
}
