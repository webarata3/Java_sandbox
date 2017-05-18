package link.webarata3.fx.invader;

import javafx.geometry.Rectangle2D;

public abstract class Sprite {
    protected double positionX;
    protected double positionY;
    protected double velocityX;
    protected double velocityY;
    protected double width;
    protected double height;

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }
}
