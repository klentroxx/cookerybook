package hu.cookerybook.core.dao;

import hu.cookerybook.core.dbconn.DatabaseFunctions;
import hu.cookerybook.core.model.PantryIngredient;
import hu.cookerybook.core.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class PantryDAOImpl implements PantryDAO {

    @Override
    public void addPantryIngredient(PantryIngredient pantryIngredient) {
        String queryString = "INSERT INTO pantry (user_id, ingredient_id, ingredient_quantity, minimum_amount) " +
                "VALUES(" + pantryIngredient.getUserId() + ", " +
                pantryIngredient.getIngredientId() + ", " +
                pantryIngredient.getIngredientQuantity() + ", " +
                pantryIngredient.getMinimumAmount() + ", " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "', " +
                "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "');";
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public void removePantryIngredient(PantryIngredient pantryIngredient) {
        String queryString = "DELETE FROM pantry WHERE id=" + pantryIngredient.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }

    @Override
    public List<PantryIngredient> getAllPantryIngredients() {
        List<PantryIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM pantry";
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            PantryIngredient pi = new PantryIngredient();
            pi.setId(parseInt(row[0]));
            pi.setUserId(parseInt(row[1]));
            pi.setIngredientId(parseInt(row[2]));
            pi.setIngredientQuantity(parseInt(row[3]));
            pi.setMinimumAmount(parseFloat(row[4]));
            result.add(pi);
        }

        return result;
    }

    @Override
    public List<PantryIngredient> getPantryIngredients(User user) {
        List<PantryIngredient> result = new ArrayList<>();
        String queryString = "SELECT * FROM pantry WHERE user_id=" + user.getId();
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);

        for (String[] row : resultSet) {
            PantryIngredient pi = new PantryIngredient();
            pi.setId(parseInt(row[0]));
            pi.setUserId(parseInt(row[1]));
            pi.setIngredientId(parseInt(row[2]));
            pi.setIngredientQuantity(parseInt(row[3]));
            pi.setMinimumAmount(parseFloat(row[4]));
            result.add(pi);
        }

        return result;
    }

    @Override
    public PantryIngredient getPantryIngredient(int id) {
        String queryString = "SELECT * FROM pantry WHERE id=" + id;
        List<String[]> resultSet = new DatabaseFunctions().getDataFromDatabase(queryString);
        String[] row = resultSet.get(0);

        PantryIngredient pi = new PantryIngredient();
        pi.setId(parseInt(row[0]));
        pi.setUserId(parseInt(row[1]));
        pi.setIngredientId(parseInt(row[2]));
        pi.setIngredientQuantity(parseInt(row[3]));
        pi.setMinimumAmount(parseFloat(row[4]));

        return pi;
    }

    @Override
    public void updatePantryIngredient(PantryIngredient pantryIngredient) {
        String queryString = "UPDATE pantry SET " +
                "user_id=" + pantryIngredient.getUserId() + ", " +
                "ingredient_id=" + pantryIngredient.getIngredientId() + ", " +
                "ingredient_quantity=" + pantryIngredient.getIngredientQuantity() + ", " +
                "minimum_amount=" + pantryIngredient.getMinimumAmount() + " " +
                "updated_at='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "' " +
                "WHERE id=" + pantryIngredient.getId();
        new DatabaseFunctions().setDataInDatabase(queryString);
    }
}
