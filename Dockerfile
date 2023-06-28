FROM jelastic/maven:3.8.6-openjdk-20.ea-b24 AS build
WORKDIR /app
COPY . .
RUN mvn clean install package -DskipTests

FROM openjdk:20-jdk-slim
VOLUME /tmp
COPY --from=build app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]