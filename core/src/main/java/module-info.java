/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import net.clementlevallois.core.PluginLifeCycleListener;
import net.clementlevallois.model.Plugin;
import org.moditect.layrry.platform.PluginLifecycleListener;

module net.clementlevallois.core {
    
    exports net.clementlevallois.core;
    requires javafx.base;
    requires javafx.graphics;
    requires transitive javafx.controls;
    requires transitive org.moditect.layrry.platform;
    requires net.clementlevallois.model;

    uses Plugin;
    provides PluginLifecycleListener with PluginLifeCycleListener;
}