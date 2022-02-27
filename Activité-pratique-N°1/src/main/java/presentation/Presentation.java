package presentation;

import dao.DaoImpl;
import extentions.DaoImpl2;
import metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
         DaoImpl dao= new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.println("resultat"+metier.calcul());
    }
}
