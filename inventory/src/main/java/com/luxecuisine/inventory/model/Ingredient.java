package com.luxecuisine.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @PositiveOrZero(message = "Quantity must be positive or zero")
    private Double quantity;

    @NotBlank(message = "Unit is required")
    private String unit;

    @PositiveOrZero(message = "Reorder level cannot be negative")
    private Double reorderLevel;

    public Ingredient() {}
    public Ingredient(String name, String category, Double quantity, String unit, Double reorderLevel) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
        this.reorderLevel = reorderLevel;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Double getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(Double reorderLevel) { this.reorderLevel = reorderLevel; }

    @Override
    public String toString() {
        return name + " (" + quantity + " " + unit + ")";
    }
}