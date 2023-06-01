# fableassignment

java version is 17
mvn version is 3.8.4
Mysql used is 8.0.33
Docker compose version is 3.8

how to run the application:
1. docker compose up (this will bring up the application and mysql both)

how to run the testing script :

1. clone the repo 
2. cd to the assignment directory 
3. sh fire_requests.sh

Details of logger application

1. Application end point for consuming the logs :  /logger/log
2. Application consumes any json format data 
3. Application port is open on 8080 (default port)
4. Currently implemented with only a defined format of logs but can be extended for other use cases.


