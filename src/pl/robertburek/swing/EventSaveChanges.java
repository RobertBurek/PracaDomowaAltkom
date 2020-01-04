package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static pl.robertburek.CarShowroom.*;

/**
 * Created by Robert Burek
 */
public class EventSaveChanges implements ActionListener, FieldsAllEvents {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (vinTextField.getText().length() > 8) vinTextField.setText(vinTextField.getText().substring(0, 8));
        BrandCar brandCar = new BrandCar(
                vinTextField.getText(),
                brandTextField.getText().toUpperCase(),
                changeUpperFristChar(modelTextField.getText().toLowerCase()),
                stringToDate(dateProdTextField.getText()),
                changeUpperFristChar(colorTextField.getText().toLowerCase()));
        brandCar.setId(Integer.valueOf(idTextField.getText()));
        try {
            updateBrandcar(brandCar);
            createListModelCars();
        } catch (SQLException e1) {
            System.out.println("Problem z zapisem zmian.");
            e1.printStackTrace();
        }
    }
}
