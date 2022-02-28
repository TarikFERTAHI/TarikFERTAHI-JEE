package metier;

import dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements IMetier{
    @Autowired
    @Qualifier("dao2")
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
