/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module plugin {
    requires pluginapi;
    requires javafx.graphics;
    requires javafx.controls;

    provides api.Plugin
            with plugin.PluginA;
    exports plugin;
}
