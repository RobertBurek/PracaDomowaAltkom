package pl.robertburek.db;

import pl.robertburek.dao.Dao;
import pl.robertburek.dao.DbDaoImplement;
import pl.robertburek.model.BrandCar;
import pl.robertburek.model.Car;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static pl.robertburek.db.OptionsDb.CREATE_TABLE;
import static pl.robertburek.db.OptionsDb.INIT_CONNECTION;
import static pl.robertburek.db.ParametersDb.*;


/**
 * Created by Robert Burek
 */
public class CreateDatebase {
    protected static Statement statement;
    private Connection connection;



    public void operationsDB(OptionsDb... options) throws SQLException {
        for (OptionsDb option : options) {
            switch (option) {
                case INIT_CONNECTION: {
                    initConnection();
                    break;
                }
                case CREATE_TABLE: {
                    createTable();
                    break;
                }
                case DROP_TABLE: {
                    dropTable();
                    break;
                }
                case CLOSE_CONNETION: {
                    closeConnection();
                    break;
                }
            }
        }
    }

    private void initConnection() {
        String dbURL = String.format("jdbc:mysql://%s:%d/%s?%s", getHOST_MY(), getPORT(), getDB_NAME(),
                getPARAM_STRING());
        try {
            connection = DriverManager.getConnection(dbURL, getUSER_NAME(), getPASSWORD());
//            DatabaseMetaData dbmd = connection.getMetaData();
//            System.out.printf("Polaczenie nawiazane poprzez: %s%n", dbmd.getDriverName());
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Problem z połączeniem!!!");
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException{
        final String SQL_DROP = "DROP TABLE IF EXISTS cars";
        statement.executeUpdate(SQL_DROP);
        System.out.println("\nTWORZENIE TABELI...");
        final String SQL_CREATE = "CREATE TABLE cars ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
//                + "id INT NOT NULL,"
                + "VIN VARCHAR(8),"
                + "brand VARCHAR(20),"
                + "model VARCHAR(20),"
                + "productionDate VARCHAR(10),"
                + "color VARCHAR(20)"
//                + "PRIMARY KEY (id))";
                + ") AUTO_INCREMENT=1";
        try {
            statement.executeUpdate(SQL_CREATE);
        } catch (SQLException e) {
            System.out.println("Problem z tworzeniem tablicy!!!");
            e.printStackTrace();
        }
    }

    private void dropTable() throws SQLException {
        System.out.println("\nUSUNIECIE TABELI...");
        final String SQL_DROP = "DROP TABLE IF EXISTS cars";
        statement.executeUpdate(SQL_DROP);
    }

    private void closeConnection() {
        try {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println("Problem z zamknięciem połączenia!!!");
            e.printStackTrace();
        }
    }

    public static void saveCars(BrandCar... cars) {
        System.out.println("\nWSTAWIANIE DANYCH...");
        for (BrandCar car : cars) {
            String insert = String.format(
                    "INSERT INTO cars VALUES (%d, '%s','%s','%s','%s', '%s')",
                    car.getId(),
                    car.getVIN(),
                    car.getBrand(),
                    car.getModel(),
                    car.getProductionDate().toString(),
                    car.getColor());
//            System.out.println(car);
            try {
                System.out.println(insert);
                statement.executeUpdate(insert);
            } catch (SQLException e) {
                System.out.println("Problem z zapisem danych do tablicy!!!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){

        List<BrandCar> brandCars = new ArrayList<>();
        Dao dao = new DbDaoImplement();

        BrandCar fordFocus = new BrandCar(1, "2q31ad3v", "FORD", "Focus", LocalDate.of(2010, 11, 12), "Zielony");
        BrandCar audiQuatro = new BrandCar(2, "3g34sd5t", "AUDI", "Quatro", LocalDate.of(2010, 11, 12), "Czerwony");
        BrandCar nissanPulsar = new BrandCar(3, "3d21gf3e", "NISSAN", "Pulsar", LocalDate.of(2015, 5, 6), "Srebrny");
        BrandCar fiatUno = new BrandCar(4, "2c21sa3w", "FIAT", "Uno", LocalDate.of(2009, 8, 24), "Biały");
        brandCars.add(fordFocus);
        brandCars.add(audiQuatro);
        brandCars.add(nissanPulsar);
        brandCars.add(fiatUno);

        try {
        dao.operationsDB(OptionsDb.INIT_CONNECTION,OptionsDb.CREATE_TABLE);
//        dao.operationsDB(OptionsDb.INIT_CONNECTION);
        saveCars(fordFocus, audiQuatro, nissanPulsar, fiatUno);
        List<BrandCar> cars = dao.getCars();
        for (Car car:cars) {
            System.out.println(car);
        }
//            System.out.println(dao.getCarById(2));
//            System.out.println(dao.deleteCarById(2));
//            dao.operationsDB(OptionsDb.DROP_TABLE,OptionsDb.CLOSE_CONNETION);
        dao.operationsDB(OptionsDb.CLOSE_CONNETION);
        } catch (SQLException e) {
            System.out.print("Błąd bazy danych: " + e.getMessage());
        }
    }
}
