/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module host {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    exports host;
    requires pluginapi;
    uses api.Plugin;
    uses api.AbstractPlugin;
}
