package pl.robertburek.dao;

import pl.robertburek.db.OptionsDb;
import pl.robertburek.db.SupportDatabase;
import pl.robertburek.model.BrandCar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.robertburek.CarShowroom.ZERO_DATE_PROD;


/**
 * Created by Robert Burek
 */
public class DbDaoImplement extends SupportDatabase implements Dao {


    @Override
    public String getNameDao() {
        return "DANE PRODUKCYJNE Z BAZY!!!";
    }


    @Override
    public boolean addCar(BrandCar brandCar) throws SQLException {
        operationsDB(OptionsDb.INIT_CONNECTION);
        String insert = String.format(
                "INSERT INTO cars(VIN, brand, model, productionDate,color) VALUES ('%s','%s','%s','%s', '%s')",
                brandCar.getVIN(),
                brandCar.getBrand(),
                brandCar.getModel(),
                brandCar.getProductionDate().toString(),
                brandCar.getColor());
        try {
            statement.executeUpdate(insert);
            return true;
        } catch (SQLException e) {
            System.out.println("PROBLEM Z ZAPISEM DANYCH DO TABLICY!!!");
            e.printStackTrace();
            return false;
        } finally {
            operationsDB(OptionsDb.CLOSE_CONNETION);
        }
    }


    @Override
    public List<BrandCar> getCars() throws SQLException {
        operationsDB(OptionsDb.INIT_CONNECTION);
        final String SQL_SELECT = "SELECT * FROM cars";
        List<BrandCar> cars = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
            while (resultSet.next()) {
                BrandCar brandCar = new BrandCar();
                resultSetCar(resultSet, brandCar);
                cars.add(brandCar);
            }
        }
        operationsDB(OptionsDb.CLOSE_CONNETION);
        return cars;
    }


    @Override
    public List<BrandCar> searchCar(Map<String, String> param) throws SQLException {
        operationsDB(OptionsDb.INIT_CONNECTION);
        String SQL_SELECT = "SELECT * FROM cars";
        if (param.get("brand") != null) {
            SQL_SELECT += " WHERE brand = '" + param.get("brand") + "'";
        }
        if (param.get("model") != null) {
            if (SQL_SELECT.contains("WHERE")) SQL_SELECT += " AND ";
            else SQL_SELECT += " WHERE ";
            SQL_SELECT += " model = '" + param.get("model") + "'";
        }
        if (param.get("productionDate") != null) {
            if (SQL_SELECT.contains("WHERE")) SQL_SELECT += " AND ";
            else SQL_SELECT += " WHERE ";
            SQL_SELECT += "productionDate = '" + param.get("productionDate") + "'";
        }
        if (param.get("VIN") != null) {
            if (SQL_SELECT.contains("WHERE")) SQL_SELECT += " AND ";
            else SQL_SELECT += " WHERE ";
            SQL_SELECT += "VIN = '" + param.get("VIN") + "'";
        }
        if (param.get("color") != null) {
            if (SQL_SELECT.contains("WHERE")) SQL_SELECT += " AND ";
            else SQL_SELECT += " WHERE ";
            SQL_SELECT += "color = '" + param.get("color") + "'";
        }
        List<BrandCar> cars = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
            while (resultSet.next()) {
                BrandCar brandCar = new BrandCar();
                resultSetCar(resultSet, brandCar);
                cars.add(brandCar);
            }
        }
        operationsDB(OptionsDb.CLOSE_CONNETION);
        return cars;
    }


    @Override
    public BrandCar getCarById(int id) throws SQLException {
        operationsDB(OptionsDb.INIT_CONNECTION);
        final String SQL_SELECT = "SELECT * FROM cars Where id=" + id;
        BrandCar brandCar = new BrandCar();
        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
            if (resultSet.next()) {
                resultSetCar(resultSet, brandCar);
            }
        }
        operationsDB(OptionsDb.CLOSE_CONNETION);
        return brandCar;
    }


    @Override
    public boolean deleteCarById(int id) throws SQLException {
        operationsDB(OptionsDb.INIT_CONNECTION);
        final String SQL_SELECT = "DELETE FROM cars Where id=" + id;
        int result = statement.executeUpdate(SQL_SELECT);
        operationsDB(OptionsDb.CLOSE_CONNETION);
        return result == 1;
    }


    @Override
    public boolean updateCar(BrandCar modifiedBrandCar) throws SQLException {
        operationsDB(OptionsDb.INIT_CONNECTION);

        final String SQL_SELECT = "SELECT * FROM cars Where id=" + modifiedBrandCar.getId();
        BrandCar oldBrandCar = new BrandCar();
        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
            if (resultSet.next()) {
                resultSetCar(resultSet, oldBrandCar);
            }
            if (!modifiedBrandCar.getBrand().equals("")) oldBrandCar.setBrand(modifiedBrandCar.getBrand());
            if (!modifiedBrandCar.getModel().equals("")) oldBrandCar.setModel(modifiedBrandCar.getModel());
            if (!modifiedBrandCar.getVIN().equals("")) oldBrandCar.setVIN(modifiedBrandCar.getVIN());
            if (!modifiedBrandCar.getColor().equals("")) oldBrandCar.setColor(modifiedBrandCar.getColor());
            if (!modifiedBrandCar.getProductionDate().toString().equals(ZERO_DATE_PROD))
                oldBrandCar.setProductionDate(modifiedBrandCar.getProductionDate());

            System.out.println("\n\nMODYFIKACJA DANYCH...");
            String updateSQL = String.format(
                    "UPDATE cars SET VIN='%s', brand='%s', model='%s', productionDate='%s',color='%s' WHERE id='%s'",
                    oldBrandCar.getVIN(),
                    oldBrandCar.getBrand(),
                    oldBrandCar.getModel(),
                    oldBrandCar.getProductionDate().toString(),
                    oldBrandCar.getColor(),
                    oldBrandCar.getId());
            try {
                statement.executeUpdate(updateSQL);
                return true;
            } catch (SQLException e) {
                System.out.println("PROBLEM Z ZAPISEM DO TABLICY!!!");
                e.printStackTrace();
                return false;
            } finally {
                operationsDB(OptionsDb.CLOSE_CONNETION);
            }
        }
    }


    private void resultSetCar(ResultSet resultSet, BrandCar brandCar) throws SQLException {
        brandCar.setId(resultSet.getInt(1));
        brandCar.setVIN(resultSet.getString(2));
        brandCar.setBrand(resultSet.getString(3));
        brandCar.setModel(resultSet.getString(4));
        String date = resultSet.getString(5);
        brandCar.setProductionDate(LocalDate.of(Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(8, 10))));
        brandCar.setColor(resultSet.getString(6));
    }


    @Override
    public boolean isIt(int id) throws SQLException {
        boolean there = false;
        operationsDB(OptionsDb.INIT_CONNECTION);
        final String SQL_SELECT = "SELECT * FROM cars Where id=" + id;
        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
            if (resultSet.next()) there = true;
        }
        operationsDB(OptionsDb.CLOSE_CONNETION);
        return there;
    }
}

