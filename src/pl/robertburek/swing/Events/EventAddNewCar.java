package pl.robertburek.swing.Events;

import pl.robertburek.model.BrandCar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static pl.robertburek.CarShowroom.*;

/**
 * Created by Robert Burek
 */
public class EventAddNewCar implements ActionListener, FieldsAllEvents {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (validationTextField()) {
            BrandCar brandCar = new BrandCar(
                    vinTextField.getText(),
                    brandTextField.getText().toUpperCase(),
                    changeUpperFirstChar(modelTextField.getText().toLowerCase()),
                    stringToDate(dateProdTextField.getText()),
                    changeUpperFirstChar(colorTextField.getText().toLowerCase()));
            try {
                methodsDao("addCar", brandCar);
                eventClearChoice.actionPerformed(e);
                eventSearch.actionPerformed(e);
            } catch (SQLException e1) {
                System.out.println("Problem z zapisem nowego rekordu.");
                e1.printStackTrace();
            }
        }
    }

}