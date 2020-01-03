package pl.robertburek.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static pl.robertburek.CarShowroom.changeUpperFristChar;
import static pl.robertburek.CarShowroom.showFoundCars;

/**
 * Created by Robert Burek
 */
public class EventSearch implements ActionListener, AllEvents {

    @Override
    public void actionPerformed(ActionEvent e) {
        informationWindow();
        Map<String, String> param = new HashMap<>();
        if (isNotEmpty(markaTextFieldSearch))
            param.put("brand", markaTextFieldSearch.getText().toUpperCase());
        if (isNotEmpty(modelTextFieldSearch))
            param.put("model", changeUpperFristChar(modelTextFieldSearch.getText()));
        if (isNotEmpty(dateProdTextFieldSearch))
            param.put("productionDate", dateProdTextFieldSearch.getText());
        if (isNotEmpty(vinTextFieldSearch))
            param.put("VIN", vinTextFieldSearch.getText());
        if (isNotEmpty(colorTextFieldSearch))
            param.put("color", changeUpperFristChar(colorTextFieldSearch.getText()));
        try {
            showFoundCars(param);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void informationWindow() {
        JOptionPane.showConfirmDialog(panel, "Szukane dane"
                        + "\n   Marka:  " + markaTextFieldSearch.getText()
                        + "\n   Model:  " + modelTextFieldSearch.getText()
                        + "\n   Rok prod.:  " + dateProdTextFieldSearch.getText()
                        + "\n   VIN:  " + vinTextFieldSearch.getText()
                        + "\n   color:  " + colorTextFieldSearch.getText(),
                "Uwaga", JOptionPane.PLAIN_MESSAGE);
    }

    private boolean isNotEmpty(JTextField TextField) {
        return TextField != null && TextField.getText().isEmpty() == false;
    }
}
