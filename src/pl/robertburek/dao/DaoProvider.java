package pl.robertburek.dao;

import pl.robertburek.db.CreateDatebase;

/**
 * Created by Robert Burek
 */
public interface DaoProvider {

    static Dao getDao(Sources sourse) {
        switch (sourse) {
            case DB:
                return new DbDaoImplement();
            case TEST:
                return new TestDaoImplement();
             default:
                return null;
        }
    }
}
