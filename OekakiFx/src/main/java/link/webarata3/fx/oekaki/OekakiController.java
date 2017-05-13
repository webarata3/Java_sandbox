package link.webarata3.fx.oekaki;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
