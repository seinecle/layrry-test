/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.core;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.ServiceLoader;
import net.clementlevallois.model.Plugin;
import net.clementlevallois.model.PluginRegistry;
import org.moditect.layrry.platform.PluginDescriptor;
import org.moditect.layrry.platform.PluginLifecycleListener;

public class PluginLifeCycleListener implements PluginLifecycleListener {
    @Override
    public void pluginAdded(PluginDescriptor pluginDescriptor) {
        ModuleLayer layer = pluginDescriptor.getModuleLayer();

        // Load all plugins
        ServiceLoader<Plugin> loader = ServiceLoader.load(layer, Plugin.class);

        // collect and filter plugins by _this_ layer
        Collection<Plugin> plugins = new LinkedHashSet<>();
        loader.forEach(plugin -> {
            if (plugin.getClass().getModule().getLayer() == layer) {
                plugins.add(plugin);
            }
        });

        // register plugins of _this_ layer
        PluginRegistry.getInstance()
            .registerPlugins(layer, plugins);
    }

    @Override
    public void pluginRemoved(PluginDescriptor pluginDescriptor) {
        // unregister plugins of _this_ layer
        PluginRegistry.getInstance()
            .unregisterPlugins(pluginDescriptor.getModuleLayer());
    }
}
