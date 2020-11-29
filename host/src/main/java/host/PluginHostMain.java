package host;

import api.PluginRegistry;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PluginHostMain extends Application {

    private static final int TILE_WIDTH = 150;
    private static final int TILE_HEIGHT = 150;

    @Override
    public void start(Stage stage) {

        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setFieldOfView(10);
        Scene scene = new Scene(new Group());
        scene.setCamera(camera);
        PluginRegistry.getInstance().initializeDeferredPlugins();
        stage.setTitle("Modular TilesFX");
        stage.setScene(scene);
        stage.setMinWidth((TILE_WIDTH * 3) + 20);
        stage.setMinHeight(TILE_HEIGHT + 30);
        stage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        Application.launch(PluginHostMain.class, args);
    }
}
