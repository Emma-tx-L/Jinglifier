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
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createUIComponents();
        setVisible(true);
    }

    private void createUIComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        Container cont = getContentPane();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 50;
        InputBox box1 = new InputBox(panel);
        InputBox box2 = new InputBox(panel);
        PlayButton button = new PlayButton();
        //add(panel, c);
        cont.add(box1, c);
        cont.add(box2);
        /*panel.add(button);*/
    }

    public static void main(String[] args) {
        new Jinglifier();
    }
}
