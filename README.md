# Goal
Creating a Javafx app:
- modular (JPMS)
- platform + plugins architecture. Plugins should be isolated in layers. Their dependencies should not conflict with each other. The platform should be able to load / unload plugins at runtime
- packaged with jlink or else, for distribution on Mac, Win and linux

# Development environment
- Windows
- Netbeans 12.1
- Maven
- JDK 15
- JavaFX 15.0.1


# Current issues
At the moment, the Javafx app (called "host") can be launched with layrry. But the plugin is not detected at run time.
