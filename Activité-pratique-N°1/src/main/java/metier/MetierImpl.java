package metier;

import dao.Idao;

public class MetierImpl implements IMetier{
    private Idao dao;
    @Override
    public double calcul() {
        double temp = dao.getData();
        double res = temp * 540 / Math.cos(temp * Math.PI);
        return res;
    }

    public void setDao(Idao dao) {
        this.dao = dao;
    }

}
