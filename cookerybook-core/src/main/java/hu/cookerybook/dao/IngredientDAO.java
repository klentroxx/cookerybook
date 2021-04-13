package hu.cookerybook.dao;

import hu.cookerybook.model.Ingredient;

import java.util.List;

public interface IngredientDAO {

    void addIngredient(Ingredient ingredient);

    void removeIngredient(Ingredient ingredient);

    List<Ingredient> getAllIngredients();

    Ingredient getIngredient(int id);

    void updateIngredient(Ingredient ingredient);
    
}
