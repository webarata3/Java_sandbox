package link.webarata3.fx.oekaki;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LineWidthCell extends ListCell<LineWidthItem> {
    private ImageView imageView;

    public LineWidthCell() {
        imageView = new ImageView();
    }

    @Override
    protected void updateItem(LineWidthItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            return;
        }

        Image icon = new Image(OekakiController.class.getResourceAsStream(item.getIconPath()));
        imageView.setImage(icon);
        setText(null);
        setGraphic(imageView);
    }
}
