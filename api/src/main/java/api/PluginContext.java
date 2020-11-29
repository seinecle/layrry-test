/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import javafx.animation.AnimationTimer;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javafx.scene.layout.GridPane;

public class PluginContext {
    private static final Random RND = new Random();

    private static PluginContext instance;
    private final int paneWidth;
    private final int paneHeight;
    private final GridPane pane;
    private final Set<HeartbeatListener> listeners = new CopyOnWriteArraySet<>();
    private final Heartbeat heartbeat;

    private PluginContext(int tileWidth, int tileHeight, GridPane pane) {
        this.paneWidth = tileWidth;
        this.paneHeight = tileHeight;
        this.pane = pane;
        this.heartbeat = new Heartbeat();
        this.heartbeat.start();
    }

    public void stop() {
        heartbeat.stop();
    }

    public int getPaneWidth() {
        return paneWidth;
    }

    public int getPaneHeight() {
        return paneHeight;
    }

    public GridPane getPane() {
        return pane;
    }

    public void addHeartbeatListener(HeartbeatListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public void removeHeartbeatListener(HeartbeatListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }

    public static PluginContext getInstance() {
        return instance;
    }

    public static PluginContext of(int tileWidth, int tileHeight, GridPane tileContainer) {
        if (null == instance) {
            instance = new PluginContext(tileWidth, tileHeight, tileContainer);
        }
        return instance;
    }

    public interface HeartbeatListener {
        void handleHeartbeat(long now, Random random);
    }

    private class Heartbeat extends AnimationTimer {
        private long lastTimerCall = System.nanoTime();

        @Override
        public void handle(long now) {
            if (now > lastTimerCall + 3_500_000_000L) {
                listeners.forEach(listener -> listener.handleHeartbeat(now, RND));
                lastTimerCall = now;
            }
        }
    }
}