/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.core;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import net.clementlevallois.model.PluginContext;

public class View {
    private final GridPane tileContainer;

    public View(int tileWidth, int tileHeight) {
        tileContainer = new GridPane();
        PluginContext.of(tileWidth, tileHeight, tileContainer);

        tileContainer.setHgap(5);
        tileContainer.setVgap(5);
        tileContainer.setAlignment(Pos.CENTER);
        tileContainer.setCenterShape(true);
        tileContainer.setPadding(new Insets(5));
        tileContainer.setBackground(new Background(new BackgroundFill(Color.web("#101214"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Node getContent() {
        return tileContainer;
    }
}