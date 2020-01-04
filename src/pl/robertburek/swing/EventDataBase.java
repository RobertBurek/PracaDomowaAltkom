package pl.robertburek.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static pl.robertburek.CarShowroom.changeDao;


/**
 * Created by Robert Burek
 */
public class EventDataBase implements ActionListener {

    static JMenu typeDB = new JMenu();
    EventClearChoice eventClearChoice;
    EventClearSearch eventClearSearch;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Baza sql")) {
            ((JMenuItem) e.getSource()).setText("Testowa");
            typeDB.setText("Produkcyjna - SQL");
        } else {
            ((JMenuItem) e.getSource()).setText("Baza sql");
            typeDB.setText("Testowa - kolekcja");
        }
        try {
            changeDao();
            eventClearChoice.actionPerformed(e);
            eventClearSearch.actionPerformed(e);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
