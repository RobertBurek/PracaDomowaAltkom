package pl.robertburek.dao;

import pl.robertburek.db.OptionsDb;
import pl.robertburek.model.BrandCar;
import pl.robertburek.model.Car;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Robert Burek
 */
public interface Dao {

//    void openDB() throws SQLException;

//    void saveCars(Car... cars) throws SQLException;

    List<Car> getCars() throws SQLException;

    Car getCarById(int id) throws SQLException;

    boolean deleteCarById(int id) throws SQLException;

//    void closeDB() throws SQLException;

    void operationDB(OptionsDb... options) throws SQLException;

}
