package link.webarata3.fx.oekaki;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Created by arata on 2017/06/09.
 */
public class ShapeCellFactory implements Callback<ListView<String>, ListCell<String>> {
    @Override
    public ListCell<String> call(ListView<String> listView) {
        return new StringShapeCell();
    }
}
