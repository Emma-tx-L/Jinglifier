package ui;

import javax.swing.*;
import java.awt.*;

public class Jinglifier extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Jinglifier() {
        super("Jinglifier");
        initializeGraphics();
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUIComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        panel.setSize(new Dimension(0, 0));
        add(panel, BorderLayout.CENTER);

        InputBox box1 = new InputBox(panel);
    }

    public static void main(String[] args) {
        new Jinglifier();
    }
}
