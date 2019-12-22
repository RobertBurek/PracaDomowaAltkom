package pl.robertburek.model;

import java.time.LocalDate;

/**
 * Created by Robert Burek
 */
public class Car {

    private int id;
    private String VIN;
    LocalDate productionDate;
    private String color;

    public Car() {
    }

    public Car(String VIN, LocalDate productionDate, String color) {
        this.VIN = VIN;
        this.productionDate = productionDate;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format(" (id: %d; VIN: %s; data produkcji: %s; kolor: %s)", id, VIN, productionDate, color);
    }
}
