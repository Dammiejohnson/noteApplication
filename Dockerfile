FROM openjdk:19
EXPOSE 8080
ADD target/note-app-docker.jar note-app-docker.jar
ENTRYPOINT ["java", "-jar", "/note-app-docker.jar"]