/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.model;

import java.util.concurrent.CompletionStage;

public interface Plugin {
    String getId();

    CompletionStage<Plugin> register(PluginContext context);

    CompletionStage<Plugin> unregister(PluginContext context);
}
