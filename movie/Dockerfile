FROM openjdk:8-jdk-alpine as builder
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} springboot-movie-api.jar
VOLUME /tmp
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/springboot-movie-api.jar"]
