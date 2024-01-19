FROM maven:3.8.4-openjdk-17 as maven-builder
COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM openjdk:17-alpine

COPY --from=maven-builder app/target/com.container.two-0.0.1-SNAPSHOT.jar /service/com.container.two-0.0.1-SNAPSHOT.jar
WORKDIR /service

EXPOSE 8000
ENTRYPOINT ["java","-jar","com.container.two-0.0.1-SNAPSHOT.jar"]