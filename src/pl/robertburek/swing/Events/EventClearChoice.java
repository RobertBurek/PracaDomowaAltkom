package pl.robertburek.swing.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Robert Burek
 */
public class EventClearChoice implements ActionListener, FieldsAllEvents {

    @Override
    public void actionPerformed(ActionEvent e) {
        idTextField.setText("");
        brandTextField.setText("");
        modelTextField.setText("");
        dateProdTextField.setText("");
        vinTextField.setText("");
        colorTextField.setText("");
        stylesDefaultTextField(brandTextField);
        stylesDefaultTextField(vinTextField);
        stylesDefaultTextField(dateProdTextField);
    }
}
