package link.webarata3.fx.invader;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InvaderController implements Initializable {
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setupEvent() {
        scene.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
        });
        drawRect();
    }

    public void drawRect(){
        gc.setFill(Color.RED);
        gc.fillRect(50, 50, 50, 20);
    }

    @FXML
    private void onKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
    }
}
