package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.Ingredient;
import hu.cookerybook.core.model.Unit;

import java.util.List;

public interface IngredientDAO {

    void addIngredient(Ingredient ingredient);

    void removeIngredient(Ingredient ingredient);

    List<Ingredient> getAllIngredients();

    List<Ingredient> getAllIngredientsSortedByName();

    Ingredient getIngredient(int id);

    Unit getUnitOfIngredient(Ingredient ingredient);

    List<Unit> getAllUnitsOfIngredient(Ingredient ingredient);

    void updateIngredient(Ingredient ingredient);
    
}
