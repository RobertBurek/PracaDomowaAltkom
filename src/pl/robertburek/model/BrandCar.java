package pl.robertburek.model;

import java.time.LocalDate;

/**
 * Created by Robert Burek
 */
public class BrandCar extends ModelCar {

    private String brand;

    public BrandCar() {
    }

    public BrandCar(String VIN, String brand, String model, LocalDate productionDate, String color) {
        super(VIN, productionDate, color, model);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
//        System.out.printf("%3s  %10s  %10s %10s %12s %11s \n", getId(),
//                getBrand(), getModel(), getVIN(), getProductionDate(), getColor());
//        return String.format("%s", brand) + super.toString();
        return String.format("%3s  %10s  %10s  %10s  %12s  %11s ", getId(),
                getBrand(), getModel(), getVIN(), getProductionDate(), getColor());
    }
}
