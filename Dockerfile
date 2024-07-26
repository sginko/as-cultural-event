FROM openjdk:21
COPY target/cultural-event-0.0.1-SNAPSHOT.jar /java/app.jar
WORKDIR /java
CMD ["java", "-jar", "app.jar"]