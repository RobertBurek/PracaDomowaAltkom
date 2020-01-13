package pl.robertburek.swing.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static pl.robertburek.CarShowroom.changeUpperFirstChar;
import static pl.robertburek.CarShowroom.showFoundCars;

/**
 * Created by Robert Burek
 */
public class EventSearch implements ActionListener, FieldsAllEvents {

    @Override
    public void actionPerformed(ActionEvent e) {
//        informationWindow("Szukane dane");
        Map<String, String> param = new HashMap<>();
        if (!brandTextFieldSearch.getText().isEmpty())
            param.put("brand", brandTextFieldSearch.getText().toUpperCase());
        if (!modelTextFieldSearch.getText().isEmpty())
            param.put("model", changeUpperFirstChar(modelTextFieldSearch.getText()));
        if (!dateProdTextFieldSearch.getText().isEmpty())
            param.put("productionDate", dateProdTextFieldSearch.getText());
        if (!vinTextFieldSearch.getText().isEmpty())
            param.put("VIN", vinTextFieldSearch.getText());
        if (!colorTextFieldSearch.getText().isEmpty())
            param.put("color", changeUpperFirstChar(colorTextFieldSearch.getText()));
        try {
            showFoundCars(param);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }


}
