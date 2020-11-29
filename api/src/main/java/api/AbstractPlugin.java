/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import javafx.application.Platform;
import javafx.scene.Node;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import javafx.scene.layout.GridPane;

public abstract class AbstractPlugin implements Plugin, PluginContext.HeartbeatListener {
    private static final String TILE_ID = "TILE_ID";
    private GridPane tile;

    @Override
    public final CompletionStage<Plugin> register(PluginContext context) {
        final CompletableFuture<Plugin> promise = new CompletableFuture<>();

        try {
            Platform.runLater(() -> {
                try {
                    createAndAddToContainer(context);
                    promise.complete(this);
                } catch (Throwable t) {
                    promise.completeExceptionally(t);
                }
            });
        } catch (IllegalStateException ise) {
            // plugin failed to initialize
            promise.completeExceptionally(ise);
        }

        return promise;
    }

    @Override
    public final CompletionStage<Plugin> unregister(PluginContext context) {
        final CompletableFuture<Plugin> promise = new CompletableFuture<>();

        try {
            Platform.runLater(() -> {
                try {
                    removeFromContainer(context);
                    promise.complete(this);
                } catch (Throwable t) {
                    promise.completeExceptionally(t);
                }
            });
        } catch (IllegalStateException iae) {
            // plugin failed to initialize
            promise.completeExceptionally(iae);
        }

        return promise;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }

    public final GridPane getTile() {
        return tile;
    }

    @Override
    public void handleHeartbeat(long now, Random random) {
        // left for implementors
    }

    private void createAndAddToContainer(PluginContext context) {
        tile = createTile(context);
        tile.getProperties().put(TILE_ID, getId());
        context.getTileContainer().getChildren().add(tile);
        context.addHeartbeatListener(this);
//        tile.setRunning(true);
    }

    private void removeFromContainer(PluginContext context) {
        context.removeHeartbeatListener(this);
        context.getTileContainer().getChildren().removeIf(this::idMatches);
//        tile.setRunning(false);
//        tile.stop();
    }

    protected abstract GridPane createTile(PluginContext context);

    private boolean idMatches(final Node node) {
        return idMatcher(getId()).apply(node);
    }

    private Function<Node, Boolean> idMatcher(final String id) {
        return node -> id.equals(node.getProperties().get(TILE_ID));
    }
}