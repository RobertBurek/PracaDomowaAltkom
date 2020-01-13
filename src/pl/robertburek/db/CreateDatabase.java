package pl.robertburek.db;

import pl.robertburek.dao.Dao;
import pl.robertburek.dao.DbDaoImplement;
import pl.robertburek.model.BrandCar;
import pl.robertburek.model.Car;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Robert Burek
 */
public class CreateDatabase extends SupportDatabase {

    public static void main(String[] args) {
        Dao dao = new DbDaoImplement();

        BrandCar fordFocus = new BrandCar("2q31ad3v", "FORD", "Focus", LocalDate.of(2010, 11, 12), "Zielony");
        BrandCar audiQuatro = new BrandCar("3g34sd5t", "AUDI", "Quatro", LocalDate.of(2010, 11, 12), "Czerwony");
        BrandCar nissanPulsar = new BrandCar("3d21gf3e", "NISSAN", "Pulsar", LocalDate.of(2015, 5, 6), "Srebrny");
        BrandCar fiatUno = new BrandCar("2c21sa3w", "FIAT", "Uno", LocalDate.of(2009, 8, 24), "Biały");
        BrandCar fiatTico = new BrandCar("2gt1sf5t", "FIAT", "Tico", LocalDate.of(2010, 8, 10), "Niebieski");
        BrandCar ford = new BrandCar("2q31w13v", "FORD", "Focus", LocalDate.of(2011, 2, 12), "Zielony");
        BrandCar ford1 = new BrandCar("2qe5ad3v", "FORD", "GT", LocalDate.of(2011, 12, 22), "Biały");
        BrandCar ford2 = new BrandCar("2qg6ad3v", "FORD", "Fiesta", LocalDate.of(2012, 8, 2), "Biały");
        BrandCar audi = new BrandCar("3g34yh5t", "AUDI", "Quatro", LocalDate.of(2009, 11, 12), "Czerwony");
        BrandCar audi1 = new BrandCar("3g354dyt", "AUDI", "1000", LocalDate.of(2008, 5, 18), "Niebieski");
        BrandCar audi2 = new BrandCar("3g354dyt", "AUDI", "200", LocalDate.of(2010, 12, 18), "Czarny");
        BrandCar nissan = new BrandCar("3dy1gfye", "NISSAN", "Pulsar", LocalDate.of(2015, 5, 16), "Niebieski");
        BrandCar nissan1 = new BrandCar("3d265f3e", "NISSAN", "Primera", LocalDate.of(2015, 7, 6), "Metalik");
        BrandCar nissan2 = new BrandCar("3d265f3e", "NISSAN", "Mikra", LocalDate.of(2015, 7, 6), "Metalik");
        BrandCar fiat = new BrandCar("2c21sa3w", "FIAT", "Uno", LocalDate.of(2009, 8, 24), "Biały");
        BrandCar fiat1 = new BrandCar("2yysa3w6", "FIAT", "Punto", LocalDate.of(2005, 8, 4), "Niebieski");
        BrandCar fiat2 = new BrandCar("2yysa3w6", "FIAT", "Uno", LocalDate.of(2005, 8, 4), "Niebieski");

        try {
            dao.operationsDB(OptionsDb.INIT_CONNECTION, OptionsDb.CREATE_TABLE);
//        dao.operationsDB(OptionsDb.INIT_CONNECTION);
            saveCars(fordFocus, audiQuatro, nissanPulsar, fiatUno, fiatTico, ford, ford1, ford2, audi, audi1, audi2, nissan,
                    nissan1, nissan2, fiat, fiat1, fiat2);
            System.out.println("W bazie danych znajdują się:");
            List<BrandCar> cars = dao.getCars();
            for (Car car : cars) {
                System.out.println(car);
            }
//        dao.operationsDB(OptionsDb.DROP_TABLE,OptionsDb.CLOSE_CONNETION);
            dao.operationsDB(OptionsDb.CLOSE_CONNETION);
        } catch (SQLException e) {
            System.out.print("Błąd bazy danych: " + e.getMessage());
        }
    }


    static void saveCars(BrandCar... cars) {
        System.out.println("\nWSTAWIANIE DANYCH...");
        for (BrandCar car : cars) {
            String insert = String.format(
                    "INSERT INTO cars(VIN, brand, model, productionDate,color) VALUES ('%s','%s','%s','%s', '%s')",
                    car.getVIN(),
                    car.getBrand(),
                    car.getModel(),
                    car.getProductionDate().toString(),
                    car.getColor());
            try {
                System.out.println(insert);
                statement.executeUpdate(insert);
            } catch (SQLException e) {
                System.out.println("Problem z zapisem danych do tablicy!!!");
                e.printStackTrace();
            }
        }
    }

}
