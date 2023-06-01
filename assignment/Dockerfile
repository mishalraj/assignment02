FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /assignment

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests


FROM openjdk:17-jdk-slim AS production


WORKDIR /assignment

COPY --from=build /assignment/target/assignment-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "assignment-0.0.1-SNAPSHOT.jar"]




