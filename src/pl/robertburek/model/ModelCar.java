package pl.robertburek.model;

import java.time.LocalDate;

/**
 * Created by Robert Burek
 */
public class ModelCar extends Car {

    private String model;

    public ModelCar() {
    }

    public ModelCar(String VIN, LocalDate productionDate, String color, String model) {
        super(VIN, productionDate, color);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format(", %s", model) + super.toString();
    }
}
