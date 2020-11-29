package plugin;

import api.AbstractPlugin;
import api.PluginContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PluginA extends AbstractPlugin {

    @Override
    protected GridPane createTile(PluginContext context) {

        GridPane pane = new GridPane();
        VBox vBox = new VBox(new Label("Léon est le plus intelligent depuis le plugin!!"));
        pane.add(vBox, 0, 1);
        return pane;

    }
}