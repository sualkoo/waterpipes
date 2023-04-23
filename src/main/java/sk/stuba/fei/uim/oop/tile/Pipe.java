package sk.stuba.fei.uim.oop.tile;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Pipe extends JPanel {
    @Setter @Getter
    protected double angle = 0;
    @Getter @Setter
    protected boolean highlighted = false;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.GRAY);
        if (highlighted) {
            g.setColor(Color.ORANGE);
            g.drawRect(0,0,getWidth()-1,getHeight()-1);
            this.setHighlighted(false);
        } else {
            g.setColor(Color.BLACK);
            g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
    }
}
