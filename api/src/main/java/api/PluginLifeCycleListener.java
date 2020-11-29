/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.ServiceLoader;
import org.moditect.layrry.platform.PluginDescriptor;
import org.moditect.layrry.platform.PluginLifecycleListener;

public class PluginLifeCycleListener implements PluginLifecycleListener {
    @Override
    public void pluginAdded(PluginDescriptor plugin) {
        ModuleLayer layer = plugin.getModuleLayer();

        // Load all plugins
        ServiceLoader<Plugin> loader = ServiceLoader.load(layer, Plugin.class);

        // collect and filter plugins by _this_ layer
        Collection<Plugin> plugins = new LinkedHashSet<>();
        loader.forEach(tilePlugin -> {
            if (tilePlugin.getClass().getModule().getLayer() == layer) {
                plugins.add(tilePlugin);
            }
        });

        // register plugins of _this_ layer
        PluginRegistry.getInstance()
            .registerPlugins(layer, plugins);
    }

    @Override
    public void pluginRemoved(PluginDescriptor plugin) {
        // unregister plugins of _this_ layer
        PluginRegistry.getInstance()
            .unregisterPlugins(plugin.getModuleLayer());
    }
}
