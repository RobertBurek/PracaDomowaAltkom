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

    private EventSearch eventSearch = new EventSearch();
    static DefaultListModel<BrandCar> brandCarDefaultListModel;
    //    JList<BrandCar> listBrandcars;
    private String nameDaoInMenuItem;
    private String currentDao;
    private EventMouseChoiceCar eventMouseChoiceCar = new EventMouseChoiceCar();


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
        JTextField idTextField = new JTextField(2);
        idTextField.setEnabled(false);
        panel.add(idTextField);
        panel.add(new JLabel("Marka", JLabel.RIGHT));
        JTextField markaTextField = new JTextField(10);
        panel.add(markaTextField);
        panel.add(new JLabel("Model", JLabel.RIGHT));
        JTextField modelTextField = new JTextField(10);
        panel.add(modelTextField);
        panel.add(new JLabel("Data prod.", JLabel.RIGHT));
        JTextField dateProdTextField = new JTextField(8);
        panel.add(dateProdTextField);
        panel.add(new JLabel("VIN", JLabel.RIGHT));
        JTextField vinTextField = new JTextField(6);
        panel.add(vinTextField);
        panel.add(new JLabel("Kolor", JLabel.RIGHT));
        JTextField colorTextField = new JTextField(10);
        panel.add(colorTextField);
        eventMouseChoiceCar.setIdTextField(idTextField);
        eventMouseChoiceCar.setMarkaTextField(markaTextField);
        eventMouseChoiceCar.setModelTextField(modelTextField);
        eventMouseChoiceCar.setDateProdTextField(dateProdTextField);
        eventMouseChoiceCar.setVinTextField(vinTextField);
        eventMouseChoiceCar.setColorTextField(colorTextField);
        panel.add(new JButton("Dodaj nowy"));
        panel.add(new JButton("Zapisz zmiany"));
        panel.add(new JButton("Usuń samochód"));
        panel.add(new JButton("Wyczyść dane"));
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
        listBrandcars.setFixedCellHeight(30);
        listBrandcars.setFixedCellWidth(600);
        eventMouseChoiceCar.setListBrandcars(listBrandcars);
        listBrandcars.addMouseListener(eventMouseChoiceCar);
        panelResualt.add(new JScrollPane(listBrandcars));
        return panelResualt;
    }
}
