package net.clementlevallois.plugin;

import net.clementlevallois.model.AbstractPlugin;
import net.clementlevallois.model.PluginContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PluginA extends AbstractPlugin {

    @Override
    protected GridPane createPane(PluginContext context) {
        System.out.println("salut du plugin!");
        GridPane pane = new GridPane();
        VBox vBox = new VBox(new Label("Léon est le plus intelligent depuis le plugin!!"));
        pane.add(vBox, 1, 0);
        return pane;

    }
}
