package pl.robertburek.dao;

import pl.robertburek.db.OptionsDb;
import pl.robertburek.model.BrandCar;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Robert Burek
 */
public interface Dao {

    String getNameDao();

    List<BrandCar> getCars() throws SQLException;

    List<BrandCar> searchCar(Map<String, String> param) throws SQLException;

    BrandCar getCarById(int id) throws SQLException;

    boolean deleteCarById(int id) throws SQLException;

    boolean addCar(BrandCar brandCar) throws SQLException;

    boolean updateCar(BrandCar brandCar) throws SQLException;

    void operationsDB(OptionsDb... options) throws SQLException;

}
