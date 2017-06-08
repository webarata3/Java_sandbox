package link.webarata3.fx.oekaki;

import javafx.scene.control.ListCell;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class StringShapeCell extends ListCell<String> {
    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item);
            Shape shape = new Line(0, 10, 20, 10);
            setGraphic(shape);
        }
    }
}
