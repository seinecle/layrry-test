/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import api.PluginContext;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PluginRegistry {
    private static final PluginRegistry INSTANCE = new PluginRegistry();
    private final Map<ModuleLayer, Map<String, Plugin>> plugins = new ConcurrentHashMap<>();
    private final Set<Plugin> deferredPlugins = new LinkedHashSet<>();

    public void registerPlugins(ModuleLayer key, Collection<Plugin> plugins) {
        PluginContext context = PluginContext.getInstance();

        Map<String, Plugin> pluginMap = this.plugins.computeIfAbsent(key, k -> new LinkedHashMap<>());

        plugins.forEach(tilePlugin -> {
            if (context == null) {
                // JavaFX Toolkit has not been initialized yet -> tilePlugin is loaded during boot time
                registerDeferredPlugin(tilePlugin);
                return;
            }

            if (!pluginMap.containsKey(tilePlugin.getId())) {
                handlePluginRegistration(tilePlugin, context);
            }
        });
    }

    public void unregisterPlugins(ModuleLayer key) {
        PluginContext context = PluginContext.getInstance();

        if (plugins.containsKey(key)) {
            plugins.get(key).values().forEach(tilePlugin -> tilePlugin.unregister(context)
                .thenAccept(this::untrackPlugin));
            plugins.remove(key);
        }
    }

    public void initializeDeferredPlugins() {
        PluginContext context = PluginContext.getInstance();

        // defensive copying to allow removal on the spot
        new LinkedHashSet<>(deferredPlugins).forEach(tilePlugin -> {
            deferredPlugins.remove(tilePlugin);
            handlePluginRegistration(tilePlugin, context);
        });
    }

    private void registerDeferredPlugin(Plugin plugin) {
        deferredPlugins.add(plugin);
    }

    private void trackPlugin(Plugin plugin) {
        ModuleLayer key = plugin.getClass().getModule().getLayer();

        plugins.computeIfAbsent(key, k -> new LinkedHashMap<>())
            .put(plugin.getId(), plugin);
    }

    private void untrackPlugin(Plugin plugin) {
        ModuleLayer key = plugin.getClass().getModule().getLayer();

        plugins.computeIfAbsent(key, k -> new LinkedHashMap<>())
            .remove(plugin.getId());
    }

    private void handlePluginRegistration(Plugin tilePlugin, PluginContext context) {
        tilePlugin.register(context)
            .thenAccept(this::trackPlugin)
            .exceptionally(throwable -> {
                // TODO: something went terribly wrong
                throwable.printStackTrace();
                return null;
            });
    }

    public static PluginRegistry getInstance() {
        return INSTANCE;
    }
}