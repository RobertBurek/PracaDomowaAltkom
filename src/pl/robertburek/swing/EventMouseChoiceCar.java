package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static pl.robertburek.swing.WindowCars.brandCarDefaultListModel;

/**
 * Created by Robert Burek
 */
public class EventMouseChoiceCar implements MouseListener {

    private JList<BrandCar> listBrandcars;
    private JTextField idTextField;
    private JTextField markaTextField;
    private JTextField modelTextField;
    private JTextField dateProdTextField;
    private JTextField vinTextField;
    private JTextField colorTextField;

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

    public void setIdTextField(JTextField idTextField) {
        this.idTextField = idTextField;
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
}
