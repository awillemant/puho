# puho
A simple webservice that provides Public Holidays

### Launch
- go to the root of the project
- execute a `mvn clean package`
- execute java `-Dloader.path="puho-strategy-fr/target" -jar puho-webservice/target/puho-webservice-1.0-SNAPSHOT.jar`

### Other country support ?
You just have to make a project very similar to the `puho-strategy-fr` one. Once it's compiled into a simple jar, you can relaunch the webservice with a new path in the `loader.path` argument.
