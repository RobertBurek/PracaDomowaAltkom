package pl.robertburek.swing.Events;

import javax.swing.*;

/**
 * Created by Robert Burek
 */
public interface FieldsAllEvents {

    JPanel panel = new JPanel();

    JTextField idTextField = new JTextField(2);
    JTextField brandTextField = new JTextField(10);
    JTextField modelTextField = new JTextField(10);
    JTextField dateProdTextField = new JTextField(8);
    JTextField vinTextField = new JTextField(6);
    JTextField colorTextField = new JTextField(10);

    JTextField brandTextFieldSearch = new JTextField(7);
    JTextField modelTextFieldSearch = new JTextField(7);
    JTextField dateProdTextFieldSearch = new JTextField(6);
    JTextField vinTextFieldSearch = new JTextField(5);
    JTextField colorTextFieldSearch = new JTextField(6);

    default void informationWindow(String title) {
        JOptionPane.showConfirmDialog(panel, title
                        + "\n   Marka:  " + brandTextFieldSearch.getText()
                        + "\n   Model:  " + modelTextFieldSearch.getText()
                        + "\n   Data prod.:  " + dateProdTextFieldSearch.getText()
                        + "\n   VIN:  " + vinTextFieldSearch.getText()
                        + "\n   Kolor:  " + colorTextFieldSearch.getText(),
                "Uwaga", JOptionPane.OK_CANCEL_OPTION);
    }
}
