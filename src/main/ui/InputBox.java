package ui;

import javax.swing.*;
import java.awt.*;

public class InputBox extends JTextField {

    private JComponent parent;

    public InputBox (JComponent parent) {
        super(1);
        this.parent = parent;
        setHorizontalAlignment(JTextField.CENTER);
        setText("Write something!");
        setEditable(true);
        setPreferredSize(new Dimension(100,100));
    }

}
