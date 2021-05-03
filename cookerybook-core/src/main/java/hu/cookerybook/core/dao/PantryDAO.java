package hu.cookerybook.core.dao;

import hu.cookerybook.core.model.PantryIngredient;
import hu.cookerybook.core.model.User;

import java.util.List;

public interface PantryDAO {

    void addPantryIngredient(PantryIngredient pantryIngredient);

    void removePantryIngredient(PantryIngredient pantryIngredient);

    List<PantryIngredient> getAllPantryIngredients();

    List<PantryIngredient> getPantryIngredients(int userId);

    PantryIngredient getPantryIngredient(int id);

    void updatePantryIngredient(PantryIngredient pantryIngredient);


}
