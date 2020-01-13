package pl.robertburek.swing.Events;

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
        if (!idTextField.getText().isEmpty() && validationTextField()) {
            BrandCar brandCar = new BrandCar(
                    vinTextField.getText(),
                    brandTextField.getText().toUpperCase(),
                    changeUpperFirstChar(modelTextField.getText().toLowerCase()),
                    stringToDate(dateProdTextField.getText()),
                    changeUpperFirstChar(colorTextField.getText().toLowerCase()));
            brandCar.setId(Integer.valueOf(idTextField.getText()));
            try {
                methodsDao("updateCar", brandCar);
                eventClearChoice.actionPerformed(e);
                eventSearch.actionPerformed(e);
            } catch (SQLException e1) {
                System.out.println("PROBLEM Z ZAPISEM ZMIAN!!!");
                e1.printStackTrace();
            }
        }
    }
}
