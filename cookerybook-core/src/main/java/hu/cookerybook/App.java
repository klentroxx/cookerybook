package hu.cookerybook;

import hu.cookerybook.dao.PantryDAO;
import hu.cookerybook.dao.PantryDAOImpl;
import hu.cookerybook.dao.UnitDAO;
import hu.cookerybook.dao.UnitDAOImpl;
import hu.cookerybook.model.Unit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        PantryDAO pdao = new PantryDAOImpl();
        UnitDAO unitDAO = new UnitDAOImpl();
        Unit unit = new Unit(1, "gramm", "g");
        Unit unit2 = new Unit(2, "kilogramm", "kg", 1, 1000);
        Unit unit3 = new Unit(3,"dekagramm", "dkg", 1, 100);
        unitDAO.updateUnit(unit);
        unitDAO.updateUnit(unit2);
        unitDAO.updateUnit(unit3);
    }
}
