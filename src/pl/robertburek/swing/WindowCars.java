package pl.robertburek.swing;

import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static pl.robertburek.swing.EventDataBase.typeDB;

/**
 * Created by Robert Burek
 */
public class WindowCars extends JFrame implements AllEvents {

    private EventSearch eventSearch = new EventSearch();
    static DefaultListModel<BrandCar> brandCarDefaultListModel;
    private String nameDaoInMenuItem;
    private String currentDao;
    private EventMouseChoiceCar eventMouseChoiceCar = new EventMouseChoiceCar();
    private EventClearChoice eventClearChoice = new EventClearChoice();


    public WindowCars(DefaultListModel<BrandCar> brandCarDefaultListModel,
                      Map<String, String> whatDao) {
        this.brandCarDefaultListModel = brandCarDefaultListModel;
        this.nameDaoInMenuItem = whatDao.get("nameDaoInMenuItem");
        this.currentDao = whatDao.get("currentDao");

        JMenuBar panelMenuBar = createMenu(nameDaoInMenuItem, currentDao);
        this.getContentPane().add(BorderLayout.NORTH, panelMenuBar);
//        setJMenuBar(panelMenuBar);

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
        panel.setLayout(new GridLayout(8, 2, 5, 5));
        panel.add(new JLabel("Id", JLabel.RIGHT));
        idTextField.setEnabled(false);
        panel.add(idTextField);
        panel.add(new JLabel("Marka", JLabel.RIGHT));
        panel.add(markaTextField);
        panel.add(new JLabel("Model", JLabel.RIGHT));
        panel.add(modelTextField);
        panel.add(new JLabel("Data prod.", JLabel.RIGHT));
        panel.add(dateProdTextField);
        panel.add(new JLabel("VIN", JLabel.RIGHT));
        panel.add(vinTextField);
        panel.add(new JLabel("Kolor", JLabel.RIGHT));
        panel.add(colorTextField);
        panel.add(new JButton("Dodaj nowy"));
        panel.add(new JButton("Zapisz zmiany"));
        panel.add(new JButton("Usuń samochód"));
        JButton clearData = new JButton("Wyczyść dane");
        clearData.addActionListener(eventClearChoice);
        panel.add(clearData);
        return panel;
    }

    private JPanel createPanelSearch() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), " Wyszukiwanie samochodów "));
        panel.add(new JLabel("Marka: "));
        panel.add(markaTextFieldSearch);
        panel.add(new JLabel("Model: "));
        panel.add(modelTextFieldSearch);
        panel.add(new JLabel("Data prod. (rrrr-mm-dd): "));
        panel.add(dateProdTextFieldSearch);
        panel.add(new JLabel("VIN (8 znaków): "));
        panel.add(vinTextFieldSearch);
        panel.add(new JLabel("Kolor: "));
        panel.add(colorTextFieldSearch);
        JButton searchButton = new JButton("Szukaj");
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
        listBrandcars.setFixedCellHeight(30);
        listBrandcars.setFixedCellWidth(600);
        eventMouseChoiceCar.setListBrandcars(listBrandcars);
        listBrandcars.addMouseListener(eventMouseChoiceCar);
        panelResualt.add(new JScrollPane(listBrandcars));
        return panelResualt;
    }
}
