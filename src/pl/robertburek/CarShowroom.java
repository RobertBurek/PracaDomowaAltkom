package pl.robertburek;

import pl.robertburek.dao.Dao;
import pl.robertburek.dao.DbDaoImplement;
import pl.robertburek.model.BrandCar;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Robert Burek
 */
public class CarShowroom {

    static List<BrandCar> brandCars = new ArrayList<>();
    static Dao dao = new DbDaoImplement();
//    static Dao dao = new TestDaoImplement();

    public static void main(String[] args) throws SQLException {
        String numberOption;
        do {
            numberOption = getOption("Wybierz opcje:\n"
                    + "[1] - Dodaj samochód\n"
                    + "[2] - Edytuj samochód\n"
                    + "[3] - Usuń samochód\n"
                    + "[4] - Pokaż samochód\n"
                    + "[5] - Lista samochodów\n"
                    + "[6] - Wyjdź \n");
            switch (numberOption) {
                case "1":
                    brandCars.add(getNewCar());
                    break;
                case "2":
                    brandCars.set(updateCar(), getNewCar());
                    break;
                case "3":
                    brandCars.remove(deleteCar());
                    break;
                case "4":
                    findCar();
                    break;
                case "5":
                    showCars();
                    break;
            }
        } while (!numberOption.equalsIgnoreCase("6"));
    }

    private static int updateCar() {
//        showCars();
        System.out.print("Podaj numer samochodu: ");
        Scanner choiceOption = new Scanner(System.in);
        return Integer.valueOf(choiceOption.next()) - 1;

    }

    private static int deleteCar() {
//        showCars();
        System.out.print("Który samochód usunąć: ");
        Scanner choiceOption = new Scanner(System.in);
        String numberOption = choiceOption.next();
        System.out.println("Usunięto : " + brandCars.get(Integer.valueOf(numberOption) - 1));
        return (Integer.valueOf(numberOption) - 1);
    }

    private static void showCars() throws SQLException {
        System.out.printf("%3s  %10s  %10s \n", "id", "Marka", "Model");
        System.out.println("---------------------------");
        brandCars = dao.getCars();
        for (BrandCar car : brandCars) {
            System.out.printf("%3s  %10s  %10s \n", (brandCars.indexOf(car) + 1),
                    car.getBrand(), car.getModel());
        }
        System.out.println("---------------------------");
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
        System.out.print("Podaj id: ");
        int id = readData.nextInt();
        System.out.print("Podaj VIN: ");
        String VIN = readData.next();
        System.out.print("Podaj markę: ");
        String brand = readData.next().toUpperCase();
        System.out.print("Podaj model: ");
        String model = changeUpperFristChar(readData.next());
        LocalDate productionDate = getProductionDate(readData);
        System.out.print("Podaj kolor: ");
        String color = readData.next();
        return new BrandCar(id, VIN, brand.toUpperCase(), model, productionDate, color);
    }

    private static LocalDate getProductionDate(Scanner readData) {
        String prodDate;
        do {
            System.out.print("Podaj datę produkcji w formacie dd/mm/yyyy : ");
            prodDate = readData.next();
        }
        while (prodDate.length() != 10);
        return LocalDate.of(Integer.parseInt(prodDate.substring(6, 10)),
                Integer.parseInt(prodDate.substring(3, 5)),
                Integer.parseInt(prodDate.substring(0, 2)));
    }

    private static String changeUpperFristChar(String model) {
        char[] modelNew = model.toCharArray();
        modelNew[0] = Character.toUpperCase(modelNew[0]);
        return new String(modelNew);
    }
}
