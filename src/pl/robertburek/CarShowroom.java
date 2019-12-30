package pl.robertburek;

import pl.robertburek.dao.Dao;
import pl.robertburek.dao.DaoProvider;
import pl.robertburek.dao.Sources;
import pl.robertburek.model.BrandCar;
import pl.robertburek.swing.WindowCars;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Robert Burek
 */

public class CarShowroom implements DaoProvider {

    public static final String ZERO_DATE_PROD = "0001-01-01";
    private static List<BrandCar> brandCars = new ArrayList<>();
    private static Dao dao;
    //    private static WindowCars windowCars;
    private static DefaultListModel<BrandCar> defaultListModel = new DefaultListModel();

//    public static List<BrandCar> getBrandCars() {
//        return brandCars;
//    }

//    public CarShowroom(DefaultListModel<BrandCar> brandCarDefaultListModel) {
//        super(brandCarDefaultListModel, "Baza sql");
//    }

    public static Dao getDao() {
        return dao;
    }

    public static void main(String[] args) throws SQLException {

//        CarShowroom salonDomaniewska = new CarShowroom();
        dao = DaoProvider.getDao(Sources.TEST);
        brandCars = dao.getCars();
        System.out.println(dao.getNameDao());
        String numberOption;
        do {
            numberOption = getOption("Wybierz opcje:\n"
                    + "[1] - Dodaj samochód\n"
                    + "[2] - Edytuj samochód\n"
                    + "[3] - Usuń samochód\n"
                    + "[4] - Pokaż samochód\n"
                    + "[5] - Lista samochodów\n"
                    + "[6] - Wyszukiwanie samochodu\n"
                    + "[7] - Interfejs okienkowy\n"
                    + "[8] - Zmiana bazy\n"
                    + "[9] - Wyjdź \n");
            String nameDaoInMenuItem;
            switch (numberOption) {
                case "1":
                    if (dao.addCar(getNewCar())) System.out.println("DANE ZAPISANE POPRAWNIE");
                    else System.out.println("BĄD W ZAPISYWANIU DANYCH!!!");
                    break;
                case "2":
                    int numberCar = readNumberCar();
                    BrandCar newBrandCar = getNewCar();
                    newBrandCar.setId(numberCar + 1);
                    dao.updateCar(newBrandCar);
                    break;
                case "3":
                    deleteCar();
                    break;
                case "4":
                    findCar();
                    break;
                case "5":
                    showCars();
                    break;
                case "6":
                    Map<String, String> param = createParamMap();
                    showFoundCars(param);
                    break;
                case "7":
                    if (getDao().getClass().getSimpleName().equals("DbDaoImplement")) {
                        nameDaoInMenuItem = "Testowa";
                    } else {
                        nameDaoInMenuItem = "Baza sql";
                    }
//                    DefaultListModel<BrandCar> defaultListModel = createListModelCars();
                    new WindowCars(createListModelCars(), nameDaoInMenuItem);
                    break;
                case "8":
                    changeDao();
                    break;
            }
        } while (!numberOption.equalsIgnoreCase("9"));
    }

    public static DefaultListModel<BrandCar> createListModelCars() {
//        brandCars = dao.getCars();
//        defaultListModel = new DefaultListModel();
        for (BrandCar brandCar : brandCars) {
            defaultListModel.addElement(brandCar);
        }
        return defaultListModel;
    }

    public static void changeDao() throws SQLException {
        if (dao.getClass().getSimpleName().equals("DbDaoImplement")) {
            dao = DaoProvider.getDao(Sources.TEST);
        } else {
            dao = DaoProvider.getDao(Sources.DB);
        }
        System.out.println(dao.getNameDao());
        brandCars = dao.getCars();
        defaultListModel.removeAllElements();
        defaultListModel=createListModelCars();
    }

    private static int readNumberCar() throws SQLException {
        showCars();
        System.out.print("Podaj numer samochodu: ");
        Scanner choiceOption = new Scanner(System.in);
        return Integer.valueOf(choiceOption.next()) - 1;

    }

    private static void deleteCar() throws SQLException {
        showCars();
        System.out.print("Który samochód usunąć: ");
        Scanner choiceOption = new Scanner(System.in);
        String numberOption = choiceOption.next();
        dao.deleteCarById(Integer.valueOf(numberOption));
    }

    private static void showCars() throws SQLException {
        System.out.println("--------------- Samochody ---------------");
        System.out.printf("%3s  %10s  %10s %12s \n", "id", "Marka", "Model", "DataProd.");
        System.out.println("-----------------------------------------");
        brandCars = dao.getCars();
        for (BrandCar car : brandCars) {
            System.out.printf("%3s  %10s  %10s %12s \n", car.getId(), car.getBrand(), car.getModel(),
                    car.getProductionDate());
        }
        System.out.println("-----------------------------------------");
    }

    public static void showFoundCars(Map<String, String> param) throws SQLException {
        System.out.println("----------------------- WYNIKI WYSZUKIWANIA --------------------");
        System.out.printf("%3s  %10s  %10s %10s %12s %11s \n", "id", "Marka", "Model", "VIN", "DataProd.", "Kolor");
        System.out.println("----------------------------------------------------------------");
        brandCars = dao.searchCar(param);
        for (BrandCar car : brandCars) {
            System.out.printf("%3s  %10s  %10s %10s %12s %11s \n", car.getId(),
                    car.getBrand(), car.getModel(), car.getVIN(), car.getProductionDate(), car.getColor());
        }
        System.out.println("----------------------------------------------------------------");
    }

    private static Map<String, String> createParamMap() {
        Map<String, String> param = new HashMap<>();
        System.out.println("Podaj szukane dane:");
        BrandCar searchedCar = getNewCar();
        if (searchedCar.getBrand().length() > 0) param.put("brand", searchedCar.getBrand());
        if (searchedCar.getModel().length() > 0) param.put("model", searchedCar.getModel());
        if (searchedCar.getVIN().length() > 0) param.put("VIN", searchedCar.getVIN());
        if (!(searchedCar.getProductionDate().toString().equals(ZERO_DATE_PROD)))
            param.put("productionDate", searchedCar.getProductionDate().toString());
        if (searchedCar.getColor().length() > 0) param.put("color", searchedCar.getColor());
        return param;
    }

    private static void findCar() throws SQLException {
        showCars();
        System.out.println(dao.getCarById(Integer.valueOf(getOption("Podaj numer: "))));
        System.out.println();
    }

    private static String getOption(String s) {
        String numberOption;
        System.out.print(s);
        Scanner choiceOption = new Scanner(System.in);
        numberOption = choiceOption.next();
        return numberOption;
    }

    private static BrandCar getNewCar() {
        Scanner readData = new Scanner(System.in);
        String VIN = getVIN(readData);
        System.out.print("Podaj markę: ");
        String brand = readData.nextLine().toUpperCase();
        System.out.print("Podaj model: ");
        String model = changeUpperFristChar(readData.nextLine());
        LocalDate productionDate = getProductionDate(readData);
        System.out.print("Podaj kolor: ");
        String color = changeUpperFristChar(readData.nextLine());
        return new BrandCar(VIN, brand.toUpperCase(), model, productionDate, color);
    }

    private static LocalDate getProductionDate(Scanner readData) {
        String prodDate;
        do {
            System.out.print("Podaj datę produkcji (yyyy-mm-dd): ");
            prodDate = readData.nextLine();
            if (prodDate.isEmpty()) {
                prodDate = ZERO_DATE_PROD;
            }
        }
        while (prodDate.length() != 10);
        return LocalDate.of(Integer.parseInt(prodDate.substring(0, 4)),
                Integer.parseInt(prodDate.substring(5, 7)),
                Integer.parseInt(prodDate.substring(8, 10)));
    }

    private static String getVIN(Scanner readData) {
        String vin;
        do {
            System.out.print("Podaj VIN (max 8 znaków): ");
            vin = readData.nextLine();
        }
        while (vin.length() > 8);
        return vin;
    }

    public static String changeUpperFristChar(String model) {
        char[] modelNew = model.toCharArray();
        if (!model.isEmpty()) {
            modelNew[0] = Character.toUpperCase(modelNew[0]);
        }
        return new String(modelNew);
    }
}
