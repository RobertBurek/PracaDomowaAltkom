package pl.robertburek.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Robert Burek
 */
public class EventClearChoice implements ActionListener, AllEvents {

    @Override
    public void actionPerformed(ActionEvent e) {
        idTextField.setText("");
        markaTextField.setText("");
        modelTextField.setText("");
        dateProdTextField.setText("");
        vinTextField.setText("");
        colorTextField.setText("");
    }
}
