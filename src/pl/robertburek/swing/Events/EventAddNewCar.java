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
        if (vinTextField.getText().length() > 8) vinTextField.setText(vinTextField.getText().substring(0, 8));
        BrandCar brandCar = new BrandCar(
                vinTextField.getText(),
                brandTextField.getText().toUpperCase(),
                changeUpperFristChar(modelTextField.getText().toLowerCase()),
                stringToDate(dateProdTextField.getText()),
                changeUpperFristChar(colorTextField.getText().toLowerCase()));
//        brandCar.setId(Integer.valueOf(idTextField.getText()));
        try {
            methodsDao("addCar", brandCar);
            eventClearChoice.actionPerformed(e);
        } catch (SQLException e1) {
            System.out.println("Problem z zapisem nowego rekordu.");
            e1.printStackTrace();
        }
    }
}