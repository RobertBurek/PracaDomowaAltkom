package pl.robertburek.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static pl.robertburek.db.ParametersDb.*;


/**
 * Created by Robert Burek
 */
public class SupportDatabase {

    private Connection connection;
    protected static Statement statement;


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
            System.out.println("PROBLEM Z POŁĄCZENIEM!!!");
            e.printStackTrace();
        }
    }


    private void createTable() throws SQLException {
        final String SQL_DROP = "DROP TABLE IF EXISTS cars";
        statement.executeUpdate(SQL_DROP);
        System.out.println("\nTWORZENIE TABELI...");
        final String SQL_CREATE = "CREATE TABLE cars ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "VIN VARCHAR(8),"
                + "brand VARCHAR(20),"
                + "model VARCHAR(20),"
                + "productionDate VARCHAR(10),"
                + "color VARCHAR(20)"
                + ") AUTO_INCREMENT=1";
        try {
            statement.executeUpdate(SQL_CREATE);
        } catch (SQLException e) {
            System.out.println("PROBLEM TWORZENIA TABELI!!!");
            e.printStackTrace();
        }
    }


    protected void dropTable() throws SQLException {
        System.out.println("\nUSUNIĘCIE TABELI...");
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
            System.out.println("PROBLEM Z ZAMKNIĘCIEM POŁĄCZENIA!!!");
            e.printStackTrace();
        }
    }

}
