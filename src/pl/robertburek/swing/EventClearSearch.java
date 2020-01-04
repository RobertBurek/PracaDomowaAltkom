package pl.robertburek.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Robert Burek
 */
public class EventClearSearch implements ActionListener, FieldsAllEvents {

    @Override
    public void actionPerformed(ActionEvent e) {
        brandTextFieldSearch.setText("");
        modelTextFieldSearch.setText("");
        dateProdTextFieldSearch.setText("");
        vinTextFieldSearch.setText("");
        colorTextFieldSearch.setText("");
    }
}
