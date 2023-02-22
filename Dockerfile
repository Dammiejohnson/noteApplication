FROM bellsoft/liberica-openjdk-alpine:latest

WORKDIR /tmp

COPY target/*.jar .

EXPOSE 8080

ENTRYPOINT java -jar note-app-docker.jar