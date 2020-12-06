package net.clementlevallois.app;

import net.clementlevallois.model.PluginRegistry;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.clementlevallois.core.View;

public class Main extends Application {

    private static final int TILE_WIDTH = 150;
private static final int TILE_HEIGHT = 150;

    @Override
    public void start(Stage stage) {
        View view = new View(TILE_WIDTH, TILE_HEIGHT);
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setFieldOfView(10);
        Scene scene = new Scene(new Group(view.getContent()));
        scene.setCamera(camera);
        PluginRegistry.getInstance().initializeDeferredPlugins();
        stage.setTitle("Modular Tiles FX");
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
        Application.launch(Main.class, args);
    }
}
