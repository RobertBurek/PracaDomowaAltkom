package pl.robertburek.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Robert Burek
 */
public class EventSearch implements ActionListener {

    JPanel panel = new JPanel();
    JTextField markaTextField = new JTextField();
    JTextField modelTextField = new JTextField();
    JTextField rokProdTextField = new JTextField();
    JTextField vinTextField = new JTextField();

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(panel, "Szukane dane"
                + "\n   Marka:  " + markaTextField.getText()
                + "\n   Model:  " + modelTextField.getText()
                + "\n   Rok prod.:  " + rokProdTextField.getText()
                + "\n   VIN:  " + vinTextField.getText(),
                "Uwaga", JOptionPane.PLAIN_MESSAGE);
    }

}
