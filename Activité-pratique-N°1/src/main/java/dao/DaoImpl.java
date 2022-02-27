package dao;

public class DaoImpl implements Idao{
    @Override
    public double getData() {
        System.out.println(" version 1");
        double temp = Math.random() * 40;
        return temp;
    }
}
