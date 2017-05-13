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

    @FXML
    private void onMousePressed(MouseEvent event) {
        gc.moveTo(event.getX(), event.getY());
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        gc.lineTo(event.getX(), event.getY());
        gc.stroke();
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

    @FXML
    private void onClickBlackButton(ActionEvent event) {
        gc.beginPath();
        gc.setStroke(Color.BLACK);
    }

    @FXML
    private void onClickRedButton(ActionEvent event) {
        gc.beginPath();
        gc.setStroke(Color.RED);
    }

    @FXML
    private void onClickGreenButton(ActionEvent event) {
        gc.beginPath();
        gc.setStroke(Color.GREEN);
    }

    @FXML
    private void onClickBlueButton(ActionEvent event) {
        gc.beginPath();
        gc.setStroke(Color.BLUE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
    }
}
