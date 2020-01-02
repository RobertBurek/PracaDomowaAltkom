package pl.robertburek.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robert Burek
 */
public interface DaoProvider {

    Map<String, String> whatDao = new HashMap<>();

    static Dao getDao(Sources sourse) {
        switch (sourse) {
            case DB:
                whatDao.clear();
                whatDao.put("nameDaoInMenuItem", "Testowa");
                whatDao.put("currentDao", "Produkcyjna - SQL");
                return new DbDaoImplement();
            case TEST:
                whatDao.clear();
                whatDao.put("nameDaoInMenuItem", "Baza sql");
                whatDao.put("currentDao", "Testowa - kolekcja");
                return new TestDaoImplement();
            default:
                return null;
        }
    }
}
