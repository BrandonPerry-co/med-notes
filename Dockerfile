FROM openjdk:17-alpine
WORKDIR /opt
EXPOSE 8082
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar