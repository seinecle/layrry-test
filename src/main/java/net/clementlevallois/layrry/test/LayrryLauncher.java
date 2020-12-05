/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.layrry.test;

import java.nio.file.Path;
import org.moditect.layrry.Layers;
import org.moditect.layrry.Resolvers;

/**
 *
 * @author LEVALLOIS
 */
public class LayrryLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Layers layers = Layers.builder().resolve(Resolvers.remote().workOffline(true))
                .layer("javafx")
                .withModule("org.openjfx:javafx-base:jar:15.0.1")
                .withModule("org.openjfx:javafx-controls:jar:15.0.1")
                .withModule("org.openjfx:javafx-graphics:jar:15.0.1")
                .withModule("org.openjfx:javafx-base:jar:win:15.0.1")
                .withModule("org.openjfx:javafx-controls:jar:win:15.0.1")
                .withModule("org.openjfx:javafx-graphics:jar:win:15.0.1")
                .layer("core")
                .withModule("net.clementlevallois:app:jar:1.0")
                .withModule("net.clementlevallois:core:jar:1.0")
                .withModule("net.clementlevallois:model:jar:1.0")
                .withModule("org.moditect.layrry:layrry-platform:1.0-SNAPSHOT")
                .withParent("javafx")
                .layer("plugins")
                .withParent("core")                
                .withModulesIn(Path.of("C:\\Users\\levallois\\Google Drive\\open\\layrry-test\\plugins"))
                .build();

        layers.run("net.clementlevallois.app/net.clementlevallois.app.Main");
    }
    
}
