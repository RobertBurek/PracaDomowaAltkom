package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.robertburek.CarShowroom.changeUpperFristChar;
import static pl.robertburek.CarShowroom.showFoundCars;

/**
 * Created by Robert Burek
 */
public class EventSearch implements ActionListener {

    JPanel panel = new JPanel();
    JTextField markaTextField = new JTextField();
    JTextField modelTextField = new JTextField();
    JTextField rokProdTextField = new JTextField();
    JTextField vinTextField = new JTextField();
    JTextField colorTextField = new JTextField();
    List<BrandCar> brandCars = new ArrayList<>();
    DefaultListModel<BrandCar> brandCarDefaultListModel;

    @Override
    public void actionPerformed(ActionEvent e) {
//        informationWindow();
        Map<String,String> param = new HashMap<>();
        if(isNotEmpty(markaTextField))
            param.put("brand", markaTextField.getText().toUpperCase());
        if(isNotEmpty(modelTextField))
            param.put("model", changeUpperFristChar(modelTextField.getText()));
        if(isNotEmpty(rokProdTextField))
            param.put("productionDate", rokProdTextField.getText());
        if(isNotEmpty(vinTextField))
            param.put("VIN", vinTextField.getText());
        if(isNotEmpty(colorTextField))
            param.put("color", changeUpperFristChar(colorTextField.getText()));
        try {
            showFoundCars(param);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void informationWindow() {
        JOptionPane.showConfirmDialog(panel, "Szukane dane"
                + "\n   Marka:  " + markaTextField.getText()
                + "\n   Model:  " + modelTextField.getText()
                + "\n   Rok prod.:  " + rokProdTextField.getText()
                + "\n   VIN:  " + vinTextField.getText()
                + "\n   color:  " + colorTextField.getText(),
                "Uwaga", JOptionPane.PLAIN_MESSAGE);
    }

    private boolean isNotEmpty(JTextField markaTextField) {
        return markaTextField != null && markaTextField.getText().isEmpty() == false;
    }

}
