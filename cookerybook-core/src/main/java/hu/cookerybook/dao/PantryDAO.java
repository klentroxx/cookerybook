package hu.cookerybook.dao;

import hu.cookerybook.model.PantryIngredient;
import hu.cookerybook.model.User;

import java.util.List;

public interface PantryDAO {

    void addPantryIngredient(PantryIngredient pantryIngredient);

    void removePantryIngredient(PantryIngredient pantryIngredient);

    List<PantryIngredient> getAllPantryIngredients();

    List<PantryIngredient> getPantryIngredients(User user);

    PantryIngredient getPantryIngredient(int id);

    void updatePantryIngredient(PantryIngredient pantryIngredient);


}
