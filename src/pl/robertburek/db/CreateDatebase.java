package pl.robertburek.db;

import pl.robertburek.dao.Dao;
import pl.robertburek.dao.DbDaoImplement;
import pl.robertburek.model.BrandCar;
import pl.robertburek.model.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

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
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Problem z połączeniem!!!");
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
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

    public static void main(String[] args) {

        Dao dao = new DbDaoImplement();

        BrandCar fordFocus = new BrandCar("2q31ad3v", "FORD", "Focus", LocalDate.of(2010, 11, 12), "Zielony");
        BrandCar audiQuatro = new BrandCar("3g34sd5t", "AUDI", "Quatro", LocalDate.of(2010, 11, 12), "Czerwony");
        BrandCar nissanPulsar = new BrandCar("3d21gf3e", "NISSAN", "Pulsar", LocalDate.of(2015, 5, 6), "Srebrny");
        BrandCar fiatUno = new BrandCar("2c21sa3w", "FIAT", "Uno", LocalDate.of(2009, 8, 24), "Biały");
        BrandCar fiatTico = new BrandCar("2gt1sf5t", "FIAT", "Tico", LocalDate.of(2010, 10, 10), "Niebieski");

        try {
            dao.operationsDB(OptionsDb.INIT_CONNECTION, OptionsDb.CREATE_TABLE);
//        dao.operationsDB(OptionsDb.INIT_CONNECTION);
            saveCars(fordFocus, audiQuatro, nissanPulsar, fiatUno, fiatTico);
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
}
