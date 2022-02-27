package presentation;

import dao.Idao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) {
        try {
            /*Scanner scanner = new Scanner(new File("config.txt"));
            String daoClassName = scanner.nextLine();
            Class cDao = Class.forName(daoClassName);
            Idao dao =(Idao)  cDao.newInstance();*/
            Scanner scan = new Scanner(new File("config.txt"));
            String daoClass = scan.next();
            String metierClass = scan.next();

            Class cDao = Class.forName(daoClass);
            Class cMetier = Class.forName(metierClass);

            Idao dao = (Idao) cDao.newInstance();
            System.out.println(dao.getData());
            IMetier metier = (IMetier) cMetier.newInstance();

            Method method1 = cMetier.getMethod("setDao",new Class[]{Idao.class});
            method1.invoke(metier,dao);
            System.out.println(metier.calcul());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
