FROM openjdk:8-jdk-alpine as builder
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} springboot-ui-api.jar
VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/springboot-ui-api.jar"]
