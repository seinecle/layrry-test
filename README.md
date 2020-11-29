# Goal
Creating a Javafx app:
- modular (JPMS)
- platform + plugins architecture. Plugins should be isolated in layers. Their dependencies should not conflict with each other. The platform should be able to load / unload plugins at runtime
- packaged with jlink or else, for distribution on Mac, Win and linux
The end goal is to create a desktop app to deliver trainings, exams and certificates, starting with  Gephi certificate. Roadmap of the project [available here](https://docs.google.com/document/d/18rcItxpQuwdJquuEkZEHstCP41iJVsfCttqkwcC7B5w/edit?usp=sharing).

# Development environment
- Windows
- Netbeans 12.1
- Maven
- JDK 15
- JavaFX 15.0.1

# General direction
I use the example of the JavaFX app "modular tiles" with layrry provided [in this repo](https://github.com/moditect/layrry-examples/tree/master/modular-tiles). I adapt it and decrease its complexity to a level I can handle.


# Current issues
At the moment, the Javafx app (called "host") can be launched with layrry. But the plugin is not detected at run time.
