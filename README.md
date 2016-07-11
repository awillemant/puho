[![travis](https://travis-ci.org/awillemant/puho.svg?branch=master)](https://travis-ci.org/awillemant/puho)
[![codecov](https://codecov.io/gh/awillemant/puho/branch/master/graph/badge.svg)](https://codecov.io/gh/awillemant/puho)
# puho
A simple webservice that provides Public Holidays


### Launch
- go to the root of the project
- execute a `mvn clean package`
- execute java `-Dloader.path="puho-strategy-fr/target" -jar puho-webservice/target/puho-webservice-1.0-SNAPSHOT.jar`

### Other country support ?
You just have to make a project very similar to the `puho-strategy-fr` one. Once it's compiled into a simple jar, you can relaunch the webservice with a new path in the `loader.path` argument.

### ReST API
Two HTTP calls are possible :
- `GET /<COUNTRY ISO CODE>/yyyy` to get all the public holidays for a given year.
- `GET /<COUNTRY ISO CODE>/yyyyMMdd/yyyyMMdd` to get all the public holidays between two dates.

List of country ISO codes : http://www.nationsonline.org/oneworld/country_code_list.htm
