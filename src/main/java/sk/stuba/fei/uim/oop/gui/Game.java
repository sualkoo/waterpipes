package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.Logic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public Game() {
        JFrame frame = new JFrame("Water Pipes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,890);
        frame.setResizable(false);
        frame.setFocusable(true);

        Logic logic = new Logic(frame);

        JPanel bottomMenu = new JPanel();
        bottomMenu.setLayout(new GridLayout(2,2));
        bottomMenu.setBackground(Color.BLACK);

        JButton resetButton = new JButton("Restart game");
        resetButton.addActionListener(logic);
        resetButton.setFocusable(false);
        JButton checkPathButton = new JButton("Check path");
        checkPathButton.addActionListener(logic);
        checkPathButton.setFocusable(false);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 8,12,8);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(2);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setBackground(Color.BLACK);
        slider.setForeground(Color.WHITE);
        slider.addChangeListener(logic);
        slider.setFocusable(false);

        JLabel levelInfo = logic.getLevelInfo();
        levelInfo.setForeground(Color.WHITE);
        JLabel boardSize = logic.getBoardSizeLabel();
        boardSize.setForeground(Color.WHITE);

        bottomMenu.add(levelInfo);
        bottomMenu.add(checkPathButton);
        bottomMenu.add(resetButton);
        bottomMenu.add(boardSize);
        bottomMenu.add(slider);

        frame.add(BorderLayout.PAGE_END ,bottomMenu);
        frame.addKeyListener(logic);
        frame.setVisible(true);
    }
}
