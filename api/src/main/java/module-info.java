/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import net.clementlevallois.model.Plugin;
import org.moditect.layrry.platform.PluginLifecycleListener;

import net.clementlevallois.core.PluginLifeCycleListener;


module pluginapi {
    exports api;
    requires javafx.graphics;
    requires org.moditect.layrry.platform;
    
    uses Plugin;
    provides PluginLifecycleListener with PluginLifeCycleListener;
}

