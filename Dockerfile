FROM maven:3.9 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR "/app"
RUN mvn clean install

FROM openjdk:17-alpine3.12

COPY --from=build /app/target/backend-0.0.1.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]