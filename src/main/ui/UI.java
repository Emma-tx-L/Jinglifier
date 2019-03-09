package ui;

import javax.swing.*;

public class UI extends JFrame {
    JTextField inputBox1;

    public void initUI() {
        setTitle("Jinglifier");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initInputBoxes();
    }

    public void initInputBoxes() {
        inputBox1 = new JTextField(1);

    }
}
