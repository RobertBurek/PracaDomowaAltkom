package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Robert Burek
 */
public class WindowCars extends JFrame {

    public static void main(String[] args) {
        new WindowCars();
    }

    public WindowCars() {
        JPanel panelSearch = createPanelSearch();
        add(panelSearch, BorderLayout.NORTH);

        JPanel panelResualtSearch = createPanelResualtSearch();
        add(panelResualtSearch, BorderLayout.CENTER);

        JPanel panelModification = createPanelModification();
        add(panelModification, BorderLayout.EAST);

        pack();
        setVisible(true);
    }

    private JPanel createPanelModification() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Edycja samochodu"));
        panel.setLayout(new GridLayout(7, 2, 5, 5));
        panel.add(new JLabel("Id", JLabel.RIGHT));
        panel.add(new JTextField(2));
        panel.add(new JLabel("Marka", JLabel.RIGHT));
        panel.add(new JTextField(10));
        panel.add(new JLabel("Model", JLabel.RIGHT));
        panel.add(new JTextField(10));
        panel.add(new JLabel("Rok prod.", JLabel.RIGHT));
        panel.add(new JTextField(8));
        panel.add(new JLabel("VIN", JLabel.RIGHT));
        panel.add(new JTextField(6));
        panel.add(new JLabel("Kolor", JLabel.RIGHT));
        panel.add(new JTextField(10));
//        panel.add(new JLabel(""));
        panel.add(new JButton("Dodaj nowy"));
        panel.add(new JButton("Zapisz zmiany"));
        return panel;
    }

    private JPanel createPanelSearch() {
        EventSearch eventHandling = new EventSearch();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Wyszukiwanie samochodów"));
        panel.add(new JLabel("Marka: "));
        JTextField markaTextField = new JTextField(10);
        panel.add(markaTextField);
        panel.add(new JLabel("Model: "));
        JTextField modelTextField = new JTextField(10);
        panel.add(modelTextField);
        panel.add(new JLabel("Rok prod. (dd/mm/rrrr): "));
        JTextField rokProdTextField = new JTextField(8);
        panel.add(rokProdTextField);
        panel.add(new JLabel("VIN (8 znaków): "));
        JTextField vinTextField = new JTextField(6);
        panel.add(vinTextField);
        JButton szukaj = new JButton("Szukaj");
        eventHandling.markaTextField = markaTextField;
        eventHandling.modelTextField = modelTextField;
        eventHandling.rokProdTextField = rokProdTextField;
        eventHandling.vinTextField = vinTextField;
        eventHandling.panel = panel;
        szukaj.addActionListener(eventHandling);
        panel.add(szukaj);
        return panel;
    }

    private JPanel createPanelResualtSearch() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Lista samochodów"));
        JList<BrandCar> listBrandcars = new JList<>();
        listBrandcars.setFixedCellHeight(25);
        listBrandcars.setFixedCellWidth(550);
//        listBrandcars.setVisibleRowCount(15);
        panel.add(new JScrollPane(listBrandcars));
        return panel;
    }


}
