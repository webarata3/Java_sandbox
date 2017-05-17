package link.webarata3.fx.invader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InvaderApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Invader.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        InvaderController invaderController = (InvaderController) loader.getController();
        invaderController.setScene(scene);
        invaderController.setupEvent();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
