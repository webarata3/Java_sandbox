package link.webarata3.fx.invader;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class InvaderController implements Initializable {
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private Ufo ufo;

    private Set<String> inputSet;

    public void setup(Scene scene) {
        ufo = new Ufo(new Image(getClass().getResourceAsStream("ufo.png")),
            0, 400, 3, 0, canvas.getWidth(), canvas.getHeight());

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                if (inputSet.contains("LEFT")) {
                    ufo.moveLeft();
                } else if (inputSet.contains("RIGHT")) {
                    ufo.moveRight();
                }
                ufo.render(gc);
            }
        }.start();
        scene.setOnKeyPressed(event -> {
            String keyCode = event.getCode().toString();
            inputSet.add(keyCode);
        });
        scene.setOnKeyReleased(event -> {
            String keyCode = event.getCode().toString();
            inputSet.remove(keyCode);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        inputSet = new HashSet<>();
    }
}
