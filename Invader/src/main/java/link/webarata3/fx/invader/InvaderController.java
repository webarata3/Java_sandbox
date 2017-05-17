package link.webarata3.fx.invader;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InvaderController implements Initializable {
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private int x = 50;
    private int y = 50;

    public void setupEvent() {
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                gc.clearRect(0, 0, 640, 512);
                gc.setFill(Color.RED);
                gc.fillRect(x, y, 50, 20);
            }
        }.start();

        scene.setOnKeyPressed(event -> {
            drawRect(event.getCode());
        });
    }

    public void drawRect(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            x = x - 1;
            if (x < 0) x = 0;
        } else if (keyCode == KeyCode.RIGHT) {
            x = x + 1;
            if (x > 640) x = 640;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
    }
}
