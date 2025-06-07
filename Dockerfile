
FROM openjdk:17-jdk-slim AS build

RUN apt-get update && \
    apt-get install -y maven

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "app.jar"]
