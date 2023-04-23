package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

public class BentPipe extends Pipe {

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
        g2.rotate(angle, getWidth() / 2, getHeight() / 2);
        this.setBackground(Color.GRAY);
        g2.setColor(Color.BLUE);
        g2.fillRect((int) (this.getWidth()*0.25), (int) (this.getHeight()*0.9), (int) (this.getWidth()*0.5),(int) (this.getHeight()*0.1));
        g2.fillRect((int) (this.getWidth()*0.9), (int) (this.getHeight()*0.25), (int) (this.getWidth()*0.1), (int) (this.getHeight()*0.5));
        g2.setColor(Color.BLACK);
        g2.drawRect((int) (this.getWidth()*0.25), (int) (this.getHeight()*0.9), (int) (this.getWidth()*0.5),(int) (this.getHeight()*0.1));
        g2.drawRect((int) (this.getWidth()*0.9), (int) (this.getHeight()*0.25), (int) (this.getWidth()*0.1), (int) (this.getHeight()*0.5));
        g2.setColor(Color.CYAN);
        g2.fillRect((int) (this.getWidth()*0.35), (int) (this.getHeight()*0.35), (int) (this.getWidth()*0.3),(int) (this.getHeight()*0.6));
        g2.fillRect((int) (this.getWidth()*0.35), (int) (this.getHeight()*0.35), (int) (this.getWidth()*0.6),(int) (this.getHeight()*0.3));
        g2.setColor(Color.BLACK);
        g2.drawLine((int) (this.getWidth()*0.35), (int) (this.getHeight()*0.35),(int) (this.getWidth()*0.35), (int) (this.getHeight()*0.95));
        g2.drawLine((int) (this.getWidth()*0.35), (int) (this.getHeight()*0.35),(int) (this.getWidth()*0.95), (int) (this.getHeight()*0.35));
        g2.drawLine((int) (this.getWidth()*0.65), (int) (this.getHeight()*0.65),(int) (this.getWidth()*0.95), (int) (this.getHeight()*0.65));
        g2.drawLine((int) (this.getWidth()*0.65), (int) (this.getHeight()*0.65),(int) (this.getWidth()*0.65), (int) (this.getHeight()*0.95));
    }
}
