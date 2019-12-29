package pl.robertburek.dao;

import pl.robertburek.db.OptionsDb;
import pl.robertburek.model.BrandCar;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Robert Burek
 */
public class TestDaoImplement implements Dao {

    static List<BrandCar> brandCars = new ArrayList<>();

    static {
        BrandCar ford = new BrandCar("2q31ad3v", "FORD", "Focus", LocalDate.of(2010, 11, 12), "Zielony");
        BrandCar ford1 = new BrandCar("2q54ad3v", "FORD", "GT", LocalDate.of(2011, 12, 22), "Biały");
        BrandCar ford2 = new BrandCar("2q54ad3v", "FORD", "Fiesta", LocalDate.of(2012, 8, 2), "Biały");
        BrandCar audi = new BrandCar("3g34sd5t", "AUDI", "Quatro", LocalDate.of(2010, 11, 12), "Czerwony");
        BrandCar audi1 = new BrandCar("3g354dyt", "AUDI", "1000", LocalDate.of(2010, 12, 18), "Niebieski");
        BrandCar audi2 = new BrandCar("3g354dyt", "AUDI", "200", LocalDate.of(2010, 12, 18), "Czarny");
        BrandCar nissan = new BrandCar("3dy1gfye", "NISSAN", "Pulsar", LocalDate.of(2015, 5, 16), "Niebieski");
        BrandCar nissan1 = new BrandCar("3d265f3e", "NISSAN", "Primera", LocalDate.of(2015, 7, 6), "Metalik");
        BrandCar nissan2 = new BrandCar("3d265f3e", "NISSAN", "Mikra", LocalDate.of(2015, 7, 6), "Metalik");
        BrandCar fiat = new BrandCar("2c21sa3w", "FIAT", "Uno", LocalDate.of(2009, 8, 24), "Biały");
        BrandCar fiat1 = new BrandCar("2yysa3w6", "FIAT", "Punto", LocalDate.of(2005, 8, 4), "Niebieski");
        BrandCar fiat2 = new BrandCar("2yysa3w6", "FIAT", "Uno", LocalDate.of(2005, 8, 4), "Niebieski");
        ford.setId(1);
        audi.setId(2);
        nissan.setId(3);
        fiat.setId(4);
        ford1.setId(5);
        audi1.setId(6);
        nissan1.setId(7);
        fiat1.setId(8);
        ford2.setId(9);
        audi2.setId(10);
        nissan2.setId(11);
        fiat2.setId(12);
        brandCars.add(ford);
        brandCars.add(audi);
        brandCars.add(nissan);
        brandCars.add(fiat);
        brandCars.add(ford1);
        brandCars.add(audi1);
        brandCars.add(nissan1);
        brandCars.add(fiat1);
        brandCars.add(ford2);
        brandCars.add(audi2);
        brandCars.add(nissan2);
        brandCars.add(fiat2);
    }

    @Override
    public String getNameDao() {
        return "Dane testowe z kolekcji!!!";
    }

    @Override
    public List<BrandCar> getCars() throws SQLException {
        return brandCars;
    }

    @Override
    public List<BrandCar> searchCar(Map<String, String> param) {
        List<BrandCar> temp1BrandCars = new ArrayList<>();
        List<BrandCar> temp2BrandCars = new ArrayList<>();
        List<BrandCar> temp3BrandCars = new ArrayList<>();
        List<BrandCar> temp4BrandCars = new ArrayList<>();
        List<BrandCar> temp5BrandCars = new ArrayList<>();
        if (param.get("brand") != null) {
            for (BrandCar brandCar : brandCars) {
                if (brandCar.getBrand().equals(param.get("brand")))
                    temp1BrandCars.add(brandCar);
            }
        } else temp1BrandCars.addAll(brandCars);
        if (param.get("model") != null) {
            for (BrandCar brandCar : temp1BrandCars) {
                if (brandCar.getModel().equals(param.get("model")))
                    temp2BrandCars.add(brandCar);
            }
        } else temp2BrandCars.addAll(temp1BrandCars);
        if (param.get("productionDate") != null) {
            for (BrandCar brandCar : temp2BrandCars) {
                if (brandCar.getProductionDate().toString().equals(param.get("productionDate")))
                    temp3BrandCars.add(brandCar);
            }
        } else temp3BrandCars.addAll(temp2BrandCars);
        if (param.get("VIN") != null) {
            for (BrandCar brandCar : temp3BrandCars) {
                if (brandCar.getVIN().equals(param.get("VIN")))
                    temp4BrandCars.add(brandCar);
            }
        } else temp4BrandCars.addAll(temp3BrandCars);
        if (param.get("color") != null) {
            for (BrandCar brandCar : temp4BrandCars) {
                if (brandCar.getColor().equals(param.get("color")))
                    temp5BrandCars.add(brandCar);
            }
        } else temp5BrandCars.addAll(temp4BrandCars);
        return temp5BrandCars;
    }

    @Override
    public BrandCar getCarById(int id) {
        return brandCars.get(id - 1);
    }

    @Override
    public boolean deleteCarById(int id) {
        brandCars.remove(id - 1);
        return true;
    }

    @Override
    public boolean addCar(BrandCar brandCar) {
        brandCar.setId(brandCars.size() + 1);
        brandCars.add(brandCar);
        return true;
    }

    @Override
    public boolean updateCar(BrandCar brandCar) {
        brandCars.set(brandCar.getId() - 1, brandCar);
        return true;
    }

    @Override
    public void operationsDB(OptionsDb... options) {

    }
}
