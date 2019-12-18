package pl.robertburek.dao;

import pl.robertburek.db.OptionsDb;
import pl.robertburek.model.BrandCar;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert Burek
 */
public class TestDaoImplement implements Dao {

    static List<BrandCar> brandCars = new ArrayList<>();

    static {
        BrandCar fordFocus = new BrandCar("2q31ad3v", "FORD", "Focus", LocalDate.of(2010, 11, 12), "Zielony");
        BrandCar audiQuatro = new BrandCar("3g34sd5t", "AUDI", "Quatro", LocalDate.of(2010, 11, 12), "Czerwony");
        BrandCar nissanPulsar = new BrandCar("3d21gf3e", "NISSAN", "Pulsar", LocalDate.of(2015, 5, 6), "Srebrny");
        BrandCar fiatUno = new BrandCar("2c21sa3w", "FIAT", "Uno", LocalDate.of(2009, 8, 24), "Biały");
        fordFocus.setId(1);
        audiQuatro.setId(2);
        nissanPulsar.setId(3);
        fiatUno.setId(4);
        brandCars.add(fordFocus);
        brandCars.add(audiQuatro);
        brandCars.add(nissanPulsar);
        brandCars.add(fiatUno);
    }

    @Override
    public List<BrandCar> getCars() throws SQLException {
        return brandCars;
    }

    @Override
    public BrandCar getCarById(int id) throws SQLException {
        return brandCars.get(id-1);
    }

    @Override
    public boolean deleteCarById(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean addCar(BrandCar brandCar) throws SQLException {
        brandCar.setId(brandCars.size()+1);
        brandCars.add(brandCar);
        return true;
    }

    @Override
    public void operationsDB(OptionsDb... options) throws SQLException {

    }
}
