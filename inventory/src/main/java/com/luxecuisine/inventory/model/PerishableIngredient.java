package com.luxecuisine.inventory.model;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PerishableIngredient extends Ingredient {
    private LocalDate expiryDate;

    public PerishableIngredient() {}
    public PerishableIngredient(String name, String category, Double quantity, String unit, Double reorderLevel, LocalDate expiryDate) {
        super(name, category, quantity, unit, reorderLevel);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    @Override
    public String toString() {
        return super.toString() + " (Expires: " + expiryDate + ")";
    }
}
