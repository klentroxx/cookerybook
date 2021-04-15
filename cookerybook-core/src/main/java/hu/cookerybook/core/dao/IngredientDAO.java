package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.Ingredient;

import java.util.List;

public interface IngredientDAO {

    void addIngredient(Ingredient ingredient);

    void removeIngredient(Ingredient ingredient);

    List<Ingredient> getAllIngredients();

    Ingredient getIngredient(int id);

    void updateIngredient(Ingredient ingredient);
    
}
