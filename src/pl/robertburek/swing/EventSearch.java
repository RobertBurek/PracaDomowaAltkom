package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static pl.robertburek.CarShowroom.*;

/**
 * Created by Robert Burek
 */
public class EventSearch implements ActionListener {

    private JPanel panel = new JPanel();
    private JTextField markaTextField;// = new JTextField();
    private JTextField modelTextField = new JTextField();
    private JTextField dateProdTextField = new JTextField();
    private JTextField vinTextField = new JTextField();
    private JTextField colorTextField = new JTextField();
    private JList<BrandCar> listBrandcars;
    private DefaultListModel<BrandCar> brandCarDefaultListModel;


    @Override
    public void actionPerformed(ActionEvent e) {
//        informationWindow();
        Map<String, String> param = new HashMap<>();
        if (isNotEmpty(markaTextField))
            param.put("brand", markaTextField.getText().toUpperCase());
        if (isNotEmpty(modelTextField))
            param.put("model", changeUpperFristChar(modelTextField.getText()));
        if (isNotEmpty(dateProdTextField))
            param.put("productionDate", dateProdTextField.getText());
        if (isNotEmpty(vinTextField))
            param.put("VIN", vinTextField.getText());
        if (isNotEmpty(colorTextField))
            param.put("color", changeUpperFristChar(colorTextField.getText()));
        try {
            showFoundCars(param);
            brandCarDefaultListModel.removeAllElements();
            brandCarDefaultListModel = createListModelCars();
            listBrandcars = new JList<>(brandCarDefaultListModel);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void informationWindow() {
        JOptionPane.showConfirmDialog(panel, "Szukane dane"
                        + "\n   Marka:  " + markaTextField.getText()
                        + "\n   Model:  " + modelTextField.getText()
                        + "\n   Rok prod.:  " + dateProdTextField.getText()
                        + "\n   VIN:  " + vinTextField.getText()
                        + "\n   color:  " + colorTextField.getText(),
                "Uwaga", JOptionPane.PLAIN_MESSAGE);
    }

    private boolean isNotEmpty(JTextField markaTextField) {
        return markaTextField != null && markaTextField.getText().isEmpty() == false;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setMarkaTextField(JTextField markaTextField) {
        this.markaTextField = markaTextField;
    }

    public void setModelTextField(JTextField modelTextField) {
        this.modelTextField = modelTextField;
    }

    public void setDateProdTextField(JTextField dateProdTextField) {
        this.dateProdTextField = dateProdTextField;
    }

    public void setVinTextField(JTextField vinTextField) {
        this.vinTextField = vinTextField;
    }

    public void setColorTextField(JTextField colorTextField) {
        this.colorTextField = colorTextField;
    }

    public void setListBrandcars(JList<BrandCar> listBrandcars) {
        this.listBrandcars = listBrandcars;
    }

    public void setBrandCarDefaultListModel(DefaultListModel<BrandCar> brandCarDefaultListModel) {
        this.brandCarDefaultListModel = brandCarDefaultListModel;
    }
}
