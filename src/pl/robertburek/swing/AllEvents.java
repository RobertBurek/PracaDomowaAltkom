package pl.robertburek.swing;

import javax.swing.*;

/**
 * Created by Robert Burek
 */
public interface AllEvents {

    JPanel panel = new JPanel();

    JTextField idTextField = new JTextField(2);
    JTextField markaTextField = new JTextField(10);
    JTextField modelTextField = new JTextField(10);
    JTextField dateProdTextField = new JTextField(8);
    JTextField vinTextField = new JTextField(6);
    JTextField colorTextField = new JTextField(10);

    JTextField markaTextFieldSearch = new JTextField(7);
    JTextField modelTextFieldSearch = new JTextField(7);
    JTextField dateProdTextFieldSearch = new JTextField(6);
    JTextField vinTextFieldSearch = new JTextField(5);
    JTextField colorTextFieldSearch = new JTextField(6);
}
