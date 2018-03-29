# Group 0437

## NOTE:

This project uses Java RMI (Remote Method Invocation) since it is a distributed application.
In order to access the login screen and use the program, a server must be running.
To launch the server, simply run the "ServerLauncher" main method. The server should launch
immediately unless there are problems with your networking.
After starting the server, simply run "MasterGUI" on every client you would like to connect
to the application. You can even run clients on different computers as long as you configure the IP
of the master server correctly. Everything should synchronize together.

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

While Gradle will automatically copy the contents of the `src/main/resources` folder to the build artifacts, please do
not delete the original copies in `src/main/resources`. They should be considered source code as much as the `*.java`
files.