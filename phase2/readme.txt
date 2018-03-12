# Group 0437

## Getting Started

This project is set up using Gradle, and as such should be imported as a Gradle project in IntelliJ. When prompted to
choose a Gradle installation, select the option to "Use default gradle wrapper". Once Gradle has finished syncing, the
main entrypoint to the program is the class `edu.toronto.csc207.restaurantsolution.Main`.

## UML
The UML diagram and PlantUML sources are in the design folder.

## Resources

Resources are stored in the `src/main/resources` folder. All the resources currently are configuration files in the
YAML format. Be careful when editing these files, YAML does not support tabs, and is whitespace sensitive. Use
two spaces to indent a level, and a hyphen (-) character to denote an array member. For more information on the
YAML format, visit http://yaml.org/.

 * events.txt

This is a YAML configuration file specifying each event to be fired. See the comments in the file for further
information on how to write your own events for testing.

 * ingredients.yml

This YAML configuration file specifies the ingredients to use.

 * menuitems.yml

This YAML configuration file specifies each menu item.

 * inventory.yml

This YAML configuration file specifies the initial amount of each item in the inventory.

While Gradle will automatically copy the contents of the `src/main/resources` folder to the build artifacts, please do
not delete the original copies in `src/main/resources`. They should be considered source code as much as the `*.java`
files.

## Services

Most of the code is contained in Services, located in the `edu.toronto.csc207.restaurantsolution.services` folder.
These services are instantiated as needed by a single `ServiceContainer` instance in the main entry point using
reflection. This architecture allows us to encapsulate what would usually be singleton objects, and instantiate
multiple dependent objects without having to manually create the dependency tree.

## Tests

There are an incomplete set of unit tests in the unittest folder, which can also be built with Gradle. These tests
mostly test the infrastructure to support Services, and deserialization of objects and events from the resources.
