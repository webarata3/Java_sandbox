package link.webarata3.fx.oekaki;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class LineWidthCellFactory implements Callback<ListView<LineWidthItem>, ListCell<LineWidthItem>> {
    @Override
    public ListCell<LineWidthItem> call(ListView<LineWidthItem> param) {
        return new LineWidthCell();
    }
}
