package extentions;

import dao.Idao;

public class DaoImpl2 implements Idao {
    @Override
    public double getData() {
        System.out.println("version 2");
        double temp = 40;
        return temp;
    }
}
