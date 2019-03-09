package ui;

import javax.swing.*;

public class InputBox extends JTextField {

    JPanel panel;

    public InputBox (JPanel parent) {

        super(1);
        this.panel = parent;
        setText("Write something!");
        setEditable(true);

    }
}
