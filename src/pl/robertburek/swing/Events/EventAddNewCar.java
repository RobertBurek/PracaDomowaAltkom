package pl.robertburek.swing.Events;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static pl.robertburek.CarShowroom.*;

/**
 * Created by Robert Burek
 */
public class EventAddNewCar implements ActionListener, FieldsAllEvents {

    @Override
    public void actionPerformed(ActionEvent e) {
        Pattern patternDate = Pattern.compile("[0-9]{4}[^\\w][0-9]{2}[^\\w][0-9]{2}");
        vinTextField.setForeground(Color.BLACK);
        dateProdTextField.setForeground(Color.BLACK);
        brandTextField.setBackground(Color.WHITE);
        if (brandTextField.getText().isEmpty()){
            brandTextField.setBackground(Color.RED);
        }else if (!Pattern.matches(String.valueOf(patternDate), dateProdTextField.getText())) {
            dateProdTextField.setForeground(Color.RED);
        } else if (vinTextField.getText().length() != 8) {
            vinTextField.setForeground(Color.RED);
        } else {
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