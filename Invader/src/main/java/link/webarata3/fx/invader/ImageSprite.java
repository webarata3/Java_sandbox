package link.webarata3.fx.invader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ImageSprite extends Sprite {
    protected Image image;

    protected double canvasWidth;
    protected double canvasHeight;

    public ImageSprite(Image image, double positionX, double positionY, double velocityX, double velocityY,
               double canvasWidth, double canvasHeight) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();

        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }
}
