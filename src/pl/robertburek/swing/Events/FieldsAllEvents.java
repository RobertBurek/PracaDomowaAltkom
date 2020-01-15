package pl.robertburek.swing.Events;

import javax.swing.*;
import java.awt.*;

import static pl.robertburek.CarShowroom.dateIsCorrect;

/**
 * Created by Robert Burek
 */
public interface FieldsAllEvents {

    EventClearChoice eventClearChoice = new EventClearChoice();
    EventClearSearch eventClearSearch = new EventClearSearch();
    EventSearch eventSearch = new EventSearch();
    int indexTListModelActive = 0;

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

    default boolean validationTextFieldChange() {
        boolean isValidationOK = true;
        if (brandTextField.getText().isEmpty()) {
            brandTextField.setBackground(Color.PINK);
            isValidationOK = false;
        } else brandTextField.setBackground(Color.WHITE);
        if (vinTextField.getText().isEmpty()) {
            vinTextField.setBackground(Color.PINK);
            isValidationOK = false;
        } else vinTextField.setBackground(Color.WHITE);
        if ((!vinTextField.getText().isEmpty()) && (vinTextField.getText().length() != 8)) {
            vinTextField.setForeground(Color.RED);
            isValidationOK = false;
        } else vinTextField.setForeground(Color.BLACK);
        if (dateProdTextField.getText().isEmpty()) {
            dateProdTextField.setBackground(Color.PINK);
            isValidationOK = false;
        } else dateProdTextField.setBackground(Color.WHITE);
        if ((!dateProdTextFieldSearch.getText().isEmpty()) &&
                (!dateIsCorrect(dateProdTextFieldSearch.getText()))) {
            dateProdTextFieldSearch.setForeground(Color.RED);
            isValidationOK = false;
        } else dateProdTextFieldSearch.setForeground(Color.BLACK);
//        Pattern patternDate = Pattern.compile("[0-9]{4}[^\\w][0-9]{2}[^\\w][0-9]{2}");
//        if ((!Pattern.matches(String.valueOf(patternDate), dateProdTextField.getText())) &&
//                (!dateProdTextField.getText().isEmpty())) {
//            dateProdTextField.setForeground(Color.RED);
//            isValidationOK = false;
//        } else dateProdTextField.setForeground(Color.BLACK);
        return isValidationOK;
    }


    default boolean validationTextFieldSearch() {
        boolean isValidationOK = true;
        if ((!vinTextFieldSearch.getText().isEmpty()) && (vinTextFieldSearch.getText().length() != 8)) {
            vinTextFieldSearch.setForeground(Color.RED);
            isValidationOK = false;
        } else vinTextFieldSearch.setForeground(Color.BLACK);
        if ((!dateProdTextFieldSearch.getText().isEmpty()) &&
                (!dateIsCorrect(dateProdTextFieldSearch.getText()))) {
            dateProdTextFieldSearch.setForeground(Color.RED);
            isValidationOK = false;
        } else dateProdTextFieldSearch.setForeground(Color.BLACK);
        return isValidationOK;
    }

    default void stylesDefaultTextField(JTextField textField) {
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
    }
}
