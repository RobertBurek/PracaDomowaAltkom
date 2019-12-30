package pl.robertburek.swing;

import pl.robertburek.CarShowroom;
import pl.robertburek.model.BrandCar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static pl.robertburek.CarShowroom.changeDao;
import static pl.robertburek.CarShowroom.createListModelCars;


/**
 * Created by Robert Burek
 */
public class EventDataBase implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Baza sql")) {
            ((JMenuItem) e.getSource()).setText("Testowa");
        } else {
            ((JMenuItem) e.getSource()).setText("Baza sql");
        }
        try {
            changeDao();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
