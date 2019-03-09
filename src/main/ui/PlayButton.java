package ui;

import javax.swing.*;


public class PlayButton extends JFrame {

    protected JButton button;

    public PlayButton() {
        createButton();
    }

    protected void createButton() {
        button = new JButton("Shape");
        button = customizeButton(button);
    }


    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }
}
