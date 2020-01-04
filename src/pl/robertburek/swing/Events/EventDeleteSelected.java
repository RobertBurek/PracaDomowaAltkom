package pl.robertburek.swing.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static pl.robertburek.CarShowroom.methodsDao;

/**
 * Created by Robert Burek
 */
public class EventDeleteSelected implements ActionListener, FieldsAllEvents {

    public EventClearChoice eventClearChoice;

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = Integer.valueOf(idTextField.getText());
        try {
            methodsDao("deleteCarById", index);
            eventClearChoice.actionPerformed(e);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
