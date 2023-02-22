FROM bellsoft/liberica-openjdk-alpine:latest

WORKDIR /tmp

COPY target/note-app-docker.jar target/note-app-docker.jar.original

EXPOSE 8080

ENTRYPOINT java -jar note-app-docker.jar