#FROM maven:3.9.2
FROM openjdk:17
#RUN #mvn clean install
COPY target/assignment-0.0.1-SNAPSHOT.jar assignment-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","assignment-0.0.1-SNAPSHOT.jar"]

# FROM maven:3.9.2-openjdk-17

# WORKDIR /assignment
# COPY . .
#
# CMD mvn spring-boot:run