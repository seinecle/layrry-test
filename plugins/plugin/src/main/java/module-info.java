/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module net.clementlevallois.plugin {
    
    requires javafx.graphics;
    requires javafx.controls;
    requires transitive net.clementlevallois.model;

    provides net.clementlevallois.model.Plugin with net.clementlevallois.plugin.PluginA;
    exports net.clementlevallois.plugin;
}
