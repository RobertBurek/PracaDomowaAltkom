package pl.robertburek.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Robert Burek
 */
public class CarDealer {
    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("Komis samochodowy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Dane");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Baza sql");
        JMenuItem m22 = new JMenuItem("Testowa");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Szukany tekst");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
//        tf.setMargin(new Insets(15, 15, 15, 15));
        JButton send = new JButton("Szukaj");
        JButton reset = new JButton("Wyczyść");
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
}
