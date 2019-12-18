package pl.robertburek.dao;

import pl.robertburek.db.OptionsDb;
import pl.robertburek.model.BrandCar;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Robert Burek
 */
public interface Dao {

    String getNameDao();

//    void openDB() throws SQLException;

//    void saveCars(Car... cars) throws SQLException;

    List<BrandCar> getCars() throws SQLException;

    BrandCar getCarById(int id) throws SQLException;

    boolean deleteCarById(int id) throws SQLException;

    boolean addCar(BrandCar brandCar) throws SQLException;

//    void closeDB() throws SQLException;

    void operationsDB(OptionsDb... options) throws SQLException;

}
