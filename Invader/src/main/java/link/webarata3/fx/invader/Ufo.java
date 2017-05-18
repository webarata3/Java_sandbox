package link.webarata3.fx.invader;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Ufo extends ImageSprite {
    public Ufo(Image image, double positionX, double positionY, double velocityX, double velocityY, double canvasWidth, double canvasHeight) {
        super(image, positionX, positionY, velocityX, velocityY, canvasWidth, canvasHeight);
    }

    public void moveLeft() {
        positionX -= velocityX;
        if (positionX < 0) {
            positionX = 0.0;
        }
    }

    public void moveRight() {
        positionX += velocityX;
        if (positionX > canvasWidth - width) {
            positionX = canvasWidth - width;
        }
    }
}
