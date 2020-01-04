package pl.robertburek.swing.Events;

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
public class EventSearch implements ActionListener, FieldsAllEvents {

    @Override
    public void actionPerformed(ActionEvent e) {
//        informationWindow("Szukane dane");
        Map<String, String> param = new HashMap<>();
        if (isNotEmpty(brandTextFieldSearch))
            param.put("brand", brandTextFieldSearch.getText().toUpperCase());
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

    private boolean isNotEmpty(JTextField TextField) {
        return TextField != null && TextField.getText().isEmpty() == false;
    }

//    private void informationWindow() {
//        JOptionPane.showConfirmDialog(panel, "Szukane dane"
//                        + "\n   Marka:  " + brandTextFieldSearch.getText()
//                        + "\n   Model:  " + modelTextFieldSearch.getText()
//                        + "\n   Rok prod.:  " + dateProdTextFieldSearch.getText()
//                        + "\n   VIN:  " + vinTextFieldSearch.getText()
//                        + "\n   color:  " + colorTextFieldSearch.getText(),
//                "Uwaga", JOptionPane.PLAIN_MESSAGE);
//    }
}
