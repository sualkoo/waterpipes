package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

public class TailPipe extends Pipe {
    private Boolean finish;
    public TailPipe(Boolean finish) {
        this.finish = finish;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.GRAY);
        if (finish) {
            g.setColor(Color.BLUE);
            g.fillRect((int) (this.getWidth()*0.25), 0, (int) (this.getWidth()*0.5),(int) (this.getHeight()*0.1));
            g.setColor(Color.BLACK);
            g.drawRect((int) (this.getWidth()*0.25), 0, (int) (this.getWidth()*0.5),(int) (this.getHeight()*0.1));
            g.setColor(Color.RED);
            g.fillRect((int) (this.getWidth()*0.35), (int) (this.getHeight()*0.1), (int) (this.getWidth()*0.3),(int) (this.getHeight()*0.9));
            g.setColor(Color.BLACK);
            g.drawRect((int) (this.getWidth()*0.35), (int) (this.getHeight()*0.1), (int) (this.getWidth()*0.3),(int) (this.getHeight()*0.9));
        } else {
            g.setColor(Color.BLUE);
            g.fillRect((int) (this.getWidth()*0.25), (int) (this.getHeight()*0.9), (int) (this.getWidth()*0.5),(int) (this.getHeight()*0.1));
            g.setColor(Color.BLACK);
            g.drawRect((int) (this.getWidth()*0.25), (int) (this.getHeight()*0.9), (int) (this.getWidth()*0.5),(int) (this.getHeight()*0.1));
            g.setColor(Color.GREEN);
            g.fillRect((int) (this.getWidth()*0.35), 0, (int) (this.getWidth()*0.3),(int) (this.getHeight()*0.9));
            g.setColor(Color.BLACK);
            g.drawRect((int) (this.getWidth()*0.35), 0, (int) (this.getWidth()*0.3),(int) (this.getHeight()*0.9));
        }
    }
}
