package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert Burek
 */
public class EventSearch implements ActionListener {

    JPanel panel = new JPanel();
    JTextField markaTextField = new JTextField();
    JTextField modelTextField = new JTextField();
    JTextField rokProdTextField = new JTextField();
    JTextField vinTextField = new JTextField();
    List<BrandCar> brandCars = new ArrayList<>();
//    DefaultListModel<BrandCar> brandCarDefaultListModel;

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(panel, "Szukane dane"
                + "\n   Marka:  " + markaTextField.getText()
                + "\n   Model:  " + modelTextField.getText()
                + "\n   Rok prod.:  " + rokProdTextField.getText()
                + "\n   VIN:  " + vinTextField.getText(),
                "Uwaga", JOptionPane.PLAIN_MESSAGE);
        String[] parameters={markaTextField.getText(),modelTextField.getText(),rokProdTextField.getText(),
                vinTextField.getText()};

        searchBrandcars(parameters,brandCars);

    }

    private void searchBrandcars(String[] parameters, List<BrandCar> brandCars) {
//        List<BrandCar> resualtListBrandcars = new ArrayList<BrandCar>(new DefaultListModel<>());
//        for (BrandCar brandCar: brandCars){

//        }
    }


}
