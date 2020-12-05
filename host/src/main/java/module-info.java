/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module net.clementlevallois.app {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    exports net.clementlevallois.app;
    requires net.clementlevallois.model;
    requires net.clementlevallois.core;
    
    uses net.clementlevallois.model.Plugin;
}
