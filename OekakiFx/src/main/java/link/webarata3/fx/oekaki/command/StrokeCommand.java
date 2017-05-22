package link.webarata3.fx.oekaki.command;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class StrokeCommand implements Command {
    private GraphicsContext gc;
    private Color color;
    private double lineWidth;
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public StrokeCommand(GraphicsContext gc, Color color, double lineWidth,
                         double startX, double startY, double endX, double endY) {
        this.gc = gc;
        this.color = color;
        this.lineWidth = lineWidth;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public void execute() {
        gc.beginPath();
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setStroke(color);
        gc.setLineWidth(lineWidth);
        gc.moveTo(startX, startY);
        gc.lineTo(endX, endY);
        gc.stroke();
    }
}
