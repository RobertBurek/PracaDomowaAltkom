package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Robert Burek
 */
public class WindowCars extends JFrame {

    private DefaultListModel<BrandCar> brandCarDefaultListModel;

    public void setBrandCarDefaultListModel(DefaultListModel<BrandCar> brandCarDefaultListModel) {
        this.brandCarDefaultListModel = brandCarDefaultListModel;
    }

    public WindowCars() throws HeadlessException {
    }

    public WindowCars(DefaultListModel<BrandCar> brandCarDefaultListModel, String currentDao) {
        this.brandCarDefaultListModel = brandCarDefaultListModel;

        JMenuBar panelMenuBar = createMenu(currentDao);
        this.getContentPane().add(BorderLayout.NORTH, panelMenuBar);

        JPanel panelMain = createPanelMain();
        add(BorderLayout.CENTER, panelMain);

        JPanel panelSearch = createPanelSearch();
        panelMain.add(panelSearch, BorderLayout.NORTH);

        JPanel panelResualtSearch = createPanelResualtSearch();
        panelMain.add(panelResualtSearch, BorderLayout.CENTER);

        JPanel panelModification = createPanelModification();
        panelMain.add(panelModification, BorderLayout.EAST);

        pack();
        setVisible(true);
    }

    private JPanel createPanelMain() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK)));
        return panel;
    }

    private JMenuBar createMenu(String currentDao) {
        EventDataBase eventDataBase = new EventDataBase();
        JMenuBar menuBar = new JMenuBar();
        JMenu menuDane = new JMenu("Dane");
        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuDane);
        menuBar.add(menuHelp);
        JMenuItem jMenuItemBaza = new JMenuItem(currentDao);
        jMenuItemBaza.addActionListener(eventDataBase);
        menuDane.add(jMenuItemBaza);
        return menuBar;
    }

    private JPanel createPanelModification() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), " Edycja samochodu "));
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
        panel.add(new JButton("Dodaj nowy"));
        panel.add(new JButton("Zapisz zmiany"));
        return panel;
    }

    private JPanel createPanelSearch() {
        EventSearch eventSearch = new EventSearch();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), " Wyszukiwanie samochodów "));
        panel.add(new JLabel("Marka: "));
        JTextField markaTextField = new JTextField(7);
        panel.add(markaTextField);
        panel.add(new JLabel("Model: "));
        JTextField modelTextField = new JTextField(7);
        panel.add(modelTextField);
        panel.add(new JLabel("Rok prod. (dd/mm/rrrr): "));
        JTextField rokProdTextField = new JTextField(6);
        panel.add(rokProdTextField);
        panel.add(new JLabel("VIN (8 znaków): "));
        JTextField vinTextField = new JTextField(5);
        panel.add(vinTextField);
        panel.add(new JLabel("Kolor: "));
        JTextField colorTextField = new JTextField(6);
        panel.add(colorTextField);
        JButton searchButton = new JButton("Szukaj");
        eventSearch.markaTextField = markaTextField;
        eventSearch.modelTextField = modelTextField;
        eventSearch.rokProdTextField = rokProdTextField;
        eventSearch.vinTextField = vinTextField;
        eventSearch.colorTextField = colorTextField;
        eventSearch.panel = panel;
        searchButton.addActionListener(eventSearch);
        panel.add(searchButton);
        return panel;
    }

    private JPanel createPanelResualtSearch() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), " Lista samochodów "));
        JList<BrandCar> listBrandcars = new JList<>(brandCarDefaultListModel);
        listBrandcars.setFixedCellHeight(25);
        listBrandcars.setFixedCellWidth(570);
        panel.add(new JScrollPane(listBrandcars));
        return panel;
    }

}
