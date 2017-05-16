package link.webarata3.fx.oekaki;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class OekakiController implements Initializable {
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private boolean isRainbowPen = false;

    @FXML
    private void onMousePressed(MouseEvent event) {
        gc.moveTo(event.getX(), event.getY());
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        gc.lineTo(event.getX(), event.getY());
        gc.stroke();
        if (isRainbowPen) {
            rainbowPen(event.getX(), event.getY(), 3);
        }
    }

    private double h = 0.0;

    private void rainbowPen(double x, double y, double transformValue) {
        gc.beginPath();
        gc.moveTo(x, y);

        gc.setStroke(ColorUtil.hsvToRgb(h, 255.0, 255.0));
        h = h + transformValue;
        if (h >= 360.0) {
            h = 0.0;
        }
    }

    @FXML
    private void onActionSaveAs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("画像ファイル", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showSaveDialog(canvas.getScene().getWindow());
        if (selectedFile != null) {
            WritableImage img = canvas.snapshot(new SnapshotParameters(), null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onActionExit(ActionEvent event) {
        Platform.exit();
    }

    private void beginPath(Color color) {
        gc.beginPath();
        gc.setStroke(color);
        isRainbowPen = false;
    }


    @FXML
    private void onClickBlackButton(ActionEvent event) {
        beginPath(Color.BLACK);
    }

    @FXML
    private void onClickRedButton(ActionEvent event) {
        beginPath(Color.RED);
    }

    @FXML
    private void onClickGreenButton(ActionEvent event) {
        beginPath(Color.GREEN);
    }

    @FXML
    private void onClickBlueButton(ActionEvent event) {
        beginPath(Color.BLUE);
    }

    @FXML
    private void onClickRainbowButton(ActionEvent event) {
        gc.beginPath();
        gc.setStroke(ColorUtil.hsvToRgb(h, 255.0, 255.0));
        isRainbowPen = true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
    }
}
