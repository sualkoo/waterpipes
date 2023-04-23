package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.board.Node;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Logic extends Adapter {
    public static final int INITIAL_BOARD_SIZE = 8;
    private final JFrame gameFrame;
    private Board board;
    @Getter
    private JLabel levelInfo;
    @Getter
    private JLabel boardSizeLabel;
    private int boardSize;
    private int level;

    public Logic(JFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.boardSize = INITIAL_BOARD_SIZE;
        this.initializeBoard(this.boardSize);
        this.gameFrame.add(this.board);
        this.levelInfo = new JLabel();
        this.boardSizeLabel = new JLabel();
        this.updateBoardSizeLabel();
        this.level = 1;
        this.updateLevel(level);
    }
    private void repaint(JFrame frame) {
        frame.revalidate();
        frame.repaint();
    }

    private void updateBoardSizeLabel () {
        this.boardSizeLabel.setText("Board size: " + this.boardSize + " x " + this.boardSize);
        this.repaint(this.gameFrame);
    }

    private void updateLevel(int level) {
        this.levelInfo.setText("Level: " + level);
        this.repaint(this.gameFrame);
    }

    private void restartGame () {
        this.gameFrame.remove(board);
        this.initializeBoard(this.boardSize);
        this.gameFrame.add(this.board);
        this.repaint(this.gameFrame);
    }

    private void initializeBoard(int dimension) {
        this.board = new Board(dimension);
        this.board.addMouseMotionListener(this);
        this.board.addMouseListener(this);
    }

    private void checkPath() {
        checkWin();
    }

    private void checkWin() {
        this.level++;
        this.updateLevel(level);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component current = this.board.getComponentAt(e.getX(), e.getY());
        if ((current instanceof Node)) {
            ((Node) current).rotate();
            this.repaint(this.gameFrame);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = this.board.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Node)) {
            return;
        }
        ((Node) current).getPipe().setHighlighted(true);
        this.repaint(this.gameFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "Check path")) {
            System.out.println(e);
            this.checkPath();
        }
        if (Objects.equals(e.getActionCommand(), "Restart game")) {
            System.out.println(e);
            this.restartGame();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.boardSize = ((JSlider) e.getSource()).getValue();
        this.updateBoardSizeLabel();
        this.restartGame();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.restartGame();
                break;
            case KeyEvent.VK_ESCAPE:
                this.gameFrame.dispose();
                break;
            case KeyEvent.VK_ENTER:
                this.checkPath();
        }
    }
}