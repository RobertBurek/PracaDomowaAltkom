package pl.robertburek.dao;

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
