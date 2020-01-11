package pl.robertburek.swing.Events;

import pl.robertburek.model.BrandCar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static pl.robertburek.CarShowroom.*;


/**
 * Created by Robert Burek
 */
public class EventSaveChanges implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        vinTextField.setForeground(Color.BLACK);
        dateProdTextField.setForeground(Color.BLACK);
        Pattern pattern = Pattern.compile("[0-9]{4}[^\\w][0-9]{2}[^\\w][0-9]{2}");
        if (!Pattern.matches(String.valueOf(pattern), dateProdTextField.getText())) {
            dateProdTextField.setForeground(Color.RED);
        } else if (vinTextField.getText().length() != 8) {
//            vinTextField.setText(vinTextField.getText().substring(0, 8));
            vinTextField.setForeground(Color.RED);
        } else {
            BrandCar brandCar = new BrandCar(
                    vinTextField.getText(),
                    brandTextField.getText().toUpperCase(),
                    changeUpperFirstChar(modelTextField.getText().toLowerCase()),
                    stringToDate(dateProdTextField.getText()),
                    changeUpperFirstChar(colorTextField.getText().toLowerCase()));
            brandCar.setId(Integer.valueOf(idTextField.getText()));
            try {
                methodsDao("updateCar", brandCar);
            } catch (SQLException e1) {
                System.out.println("Problem z zapisem zmian.");
                e1.printStackTrace();
            }
        }
    }
}
