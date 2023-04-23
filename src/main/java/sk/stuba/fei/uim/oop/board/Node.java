package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.tile.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Node extends JPanel {
    @Getter
    private Map<Direction, Node> neighbours;
    @Getter @Setter
    private boolean finish;
    @Getter
    private int posX;
    @Getter
    private int posY;
    @Getter @Setter
    private State state;
    @Getter
    private Pipe pipe;
    private double angle;

    public Node(int posX, int posY) {
        this.neighbours = new HashMap<>();
        this.finish = false;
        this.posX = posX;
        this.posY = posY;
        this.state = State.EMPTY;
        this.setLayout(new GridLayout(1,1));
        this.pipe = new Pipe();
    }

    public void addNeighbour(Direction direction, Node node) {
        this.neighbours.put(direction, node);
    }

    @Override
    public void paintComponent(Graphics g) {
        switch (this.state) {
            case BENT:
                this.pipe = new BentPipe();
                this.add(pipe);
                break;
            case STRAIGHT:
                this.pipe = new StraightPipe();
                this.add(pipe);
                break;
            case TAIL:
                this.pipe = new TailPipe(this.finish);
                this.add(pipe);
                break;
            default:
                this.add(pipe);
                break;
        }
    }

    public void rotate() {
        this.angle += Math.PI / 2;
        this.pipe.setAngle(this.angle);
        this.repaint();
    }
}
