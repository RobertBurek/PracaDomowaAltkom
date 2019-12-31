package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static pl.robertburek.swing.EventDataBase.typeDB;

/**
 * Created by Robert Burek
 */
public class WindowCars extends JFrame {

    EventSearch eventSearch = new EventSearch();
    static DefaultListModel<BrandCar> brandCarDefaultListModel;
    private String nameDaoInMenuItem;
    private String currentDao;



    public WindowCars(DefaultListModel<BrandCar> brandCarDefaultListModel,
                      Map<String, String> whatDao) {
        this.brandCarDefaultListModel = brandCarDefaultListModel;
        this.nameDaoInMenuItem = whatDao.get("nameDaoInMenuItem");
        this.currentDao = whatDao.get("currentDao");

        JMenuBar panelMenuBar = createMenu(nameDaoInMenuItem, currentDao);
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

    private JMenuBar createMenu(String nameDaoInMenuItem, String currentDao) {
        EventDataBase eventDataBase = new EventDataBase();
        JMenuBar menuBar = new JMenuBar();
        JMenu menuDane = new JMenu("Dane");
        JMenu menuHelp = new JMenu("Help");
        JMenu menuSpace = new JMenu("                  ");
        JMenu menuDB = new JMenu("Baza:");
        typeDB.setText(currentDao);
        menuBar.add(menuDane);
        menuBar.add(menuHelp);
        menuBar.add(menuSpace).setEnabled(false);
        menuBar.add(menuDB).setEnabled(false);
        menuBar.add(typeDB).setEnabled(false);
        JMenuItem jMenuItemBaza = new JMenuItem(nameDaoInMenuItem);
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
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), " Wyszukiwanie samochodów "));
        panel.add(new JLabel("Marka: "));
        JTextField markaTextField = new JTextField(7);
        panel.add(markaTextField);
        panel.add(new JLabel("Model: "));
        JTextField modelTextField = new JTextField(7);
        panel.add(modelTextField);
        panel.add(new JLabel("Data prod. (rrrr-mm-dd): "));
        JTextField dateProdTextField = new JTextField(6);
        panel.add(dateProdTextField);
        panel.add(new JLabel("VIN (8 znaków): "));
        JTextField vinTextField = new JTextField(5);
        panel.add(vinTextField);
        panel.add(new JLabel("Kolor: "));
        JTextField colorTextField = new JTextField(6);
        panel.add(colorTextField);
        JButton searchButton = new JButton("Szukaj");
        eventSearch.setMarkaTextField(markaTextField);
        eventSearch.setModelTextField(modelTextField);
        eventSearch.setDateProdTextField(dateProdTextField);
        eventSearch.setVinTextField(vinTextField);
        eventSearch.setColorTextField(colorTextField);
        eventSearch.setPanel(panel);
        searchButton.addActionListener(eventSearch);
        panel.add(searchButton);
        return panel;
    }

    private JPanel createPanelResualtSearch() {
        JPanel panelResualt = new JPanel();
        panelResualt.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), " Lista samochodów "));
        System.out.println(currentDao);
        JList<BrandCar> listBrandcars = new JList<>(brandCarDefaultListModel);
        listBrandcars.setFixedCellHeight(25);
        listBrandcars.setFixedCellWidth(570);
        panelResualt.add(new JScrollPane(listBrandcars));
        return panelResualt;
    }
}
