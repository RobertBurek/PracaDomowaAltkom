package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static pl.robertburek.swing.WindowCars.brandCarDefaultListModel;

/**
 * Created by Robert Burek
 */
public class EventMouseChoiceCar implements MouseListener, AllEvents {

    private JList<BrandCar> listBrandcars;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int choiceIndex = listBrandcars.locationToIndex(e.getPoint());
            BrandCar brandCar = brandCarDefaultListModel.getElementAt(choiceIndex);
            showChoiceCar(brandCar);
        }
    }

    private void showChoiceCar(BrandCar brandCar) {
        idTextField.setText(String.valueOf(brandCar.getId()));
        markaTextField.setText(brandCar.getBrand());
        modelTextField.setText(brandCar.getModel());
        dateProdTextField.setText(brandCar.getProductionDate().toString());
        vinTextField.setText(brandCar.getVIN());
        colorTextField.setText(brandCar.getColor());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void setListBrandcars(JList<BrandCar> listBrandcars) {
        this.listBrandcars = listBrandcars;
    }
}
