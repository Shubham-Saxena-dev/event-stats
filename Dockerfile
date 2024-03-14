FROM openjdk:11-jre-slim-buster
WORKDIR /app
COPY . /app
COPY target/HelloFreshExercise-1.0-SNAPSHOT.jar HelloFreshExercise.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "HelloFreshExercise.jar"]
