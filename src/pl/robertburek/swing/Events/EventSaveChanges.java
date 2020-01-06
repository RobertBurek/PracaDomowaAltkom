package pl.robertburek.swing.Events;

import pl.robertburek.model.BrandCar;

import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static java.awt.Color.BLUE;
import static java.awt.Color.red;
import static pl.robertburek.CarShowroom.*;


/**
 * Created by Robert Burek
 */
public class EventSaveChanges implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        vinTextField.setForeground(Color.BLACK);
        dateProdTextField.setForeground(Color.BLACK);
        if (vinTextField.getText().length() > 8) {
            vinTextField.setText(vinTextField.getText().substring(0, 8));
            vinTextField.setForeground(Color.RED);
        }
        Pattern pattern = Pattern.compile("[0-9]{4}[^\\w][0-9]{2}[^\\w][0-9]{2}");
        if (!Pattern.matches(String.valueOf(pattern), dateProdTextField.getText())) {
            dateProdTextField.setForeground(Color.RED);
        }
        BrandCar brandCar = new BrandCar(
                vinTextField.getText(),
                brandTextField.getText().toUpperCase(),
                changeUpperFristChar(modelTextField.getText().toLowerCase()),
                stringToDate(dateProdTextField.getText()),
                changeUpperFristChar(colorTextField.getText().toLowerCase()));
        brandCar.setId(Integer.valueOf(idTextField.getText()));
        try {
            methodsDao("updateCar",brandCar);
        } catch (SQLException e1) {
            System.out.println("Problem z zapisem zmian.");
            e1.printStackTrace();
        }
    }
}
