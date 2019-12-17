package pl.robertburek.dao;

import pl.robertburek.db.OptionsDb;
import pl.robertburek.model.BrandCar;
import pl.robertburek.model.Car;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert Burek
 */
public class ArrayDaoImplement implements Dao {

    static List<Car> brandCars = new ArrayList<>();

    static {
        BrandCar fordFocus = new BrandCar(1, "2q31ad3v", "FORD", "Focus", LocalDate.of(2010, 11, 12), "Zielony");
        BrandCar audiQuatro = new BrandCar(2, "3g34sd5t", "AUDI", "Quatro", LocalDate.of(2010, 11, 12), "Czerwony");
        BrandCar nissanPulsar = new BrandCar(3, "3d21gf3e", "NISSAN", "Pulsar", LocalDate.of(2015, 5, 6), "Srebrny");
        BrandCar fiatUno = new BrandCar(4, "2c21sa3w", "FIAT", "Uno", LocalDate.of(2009, 8, 24), "Biały");
        brandCars.add(fordFocus);
        brandCars.add(audiQuatro);
        brandCars.add(nissanPulsar);
        brandCars.add(fiatUno);
    }

    @Override
    public List<Car> getCars() throws SQLException {
        return brandCars;
    }

    @Override
    public Car getCarById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteCarById(int id) throws SQLException {
        return false;
    }

    @Override
    public void operationDB(OptionsDb... options) throws SQLException {

    }
}
