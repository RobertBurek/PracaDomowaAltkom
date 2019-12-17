package pl.robertburek.db;

import pl.robertburek.model.BrandCar;

import java.sql.*;

import static pl.robertburek.db.ParametersDb.*;


/**
 * Created by Robert Burek
 */
public class CreateDatebase {
    protected Statement statement;
    private Connection connection;

    public void operationDB(OptionsDb... options) throws SQLException {
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
            DatabaseMetaData dbmd = connection.getMetaData();
            System.out.printf("Polaczenie nawiazane poprzez: %s%n", dbmd.getDriverName());
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

    public void saveCars(BrandCar... cars) {
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
}
