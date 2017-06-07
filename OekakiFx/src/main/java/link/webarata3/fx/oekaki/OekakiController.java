package link.webarata3.fx.oekaki;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import link.webarata3.fx.oekaki.command.Command;
import link.webarata3.fx.oekaki.command.CommandHistory;
import link.webarata3.fx.oekaki.command.CommandUnit;
import link.webarata3.fx.oekaki.command.StrokeCommand;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OekakiController implements Initializable {
    @FXML
    private Canvas canvas;

    @FXML
    private ComboBox lineWidthComboBox;

    private GraphicsContext gc;

    private Color currentColor;
    private double currentLineWidth;
    private double startX;
    private double startY;

    private CommandHistory commandHistory;
    private CommandUnit currentCommandUnit;

    @FXML
    private void onMousePressed(MouseEvent event) {
        currentCommandUnit = new CommandUnit();
        commandHistory.add(currentCommandUnit);
        startX = event.getX();
        startY = event.getY();
    }

    @FXML
    private void onMouseReleased(MouseEvent event) {
        Command command = new StrokeCommand(gc, currentColor, currentLineWidth, startX, startY, event.getX(), event.getY());
        command.execute();
        currentCommandUnit.add(command);
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        Command command = new StrokeCommand(gc, currentColor, currentLineWidth, startX, startY, event.getX(), event.getY());
        command.execute();
        currentCommandUnit.add(command);
        startX = event.getX();
        startY = event.getY();
    }

    private double h = 0.0;

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

    private void stroke(Color color) {
        currentColor = color;
    }

    @FXML
    private void onClickColorPicker(ActionEvent event) {
        stroke(((ColorPicker) event.getSource()).getValue());
    }

    @FXML
    private void onClickLineWidthComboBOx(ActionEvent event) {
        currentLineWidth = Integer.parseInt(lineWidthComboBox.getValue().toString());
    }

    @FXML
    private void onClickBoldButton(ActionEvent event) {
        currentLineWidth += 1;
    }

    @FXML
    private void onClickUndoButton(ActionEvent event) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        commandHistory.undo();
        commandHistory.execute();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        commandHistory = new CommandHistory();
        currentLineWidth = 1;

        ObservableList<Integer> options = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 10, 16, 24, 36);
        lineWidthComboBox.setItems(options);
        lineWidthComboBox.getSelectionModel().selectFirst();
    }
}
