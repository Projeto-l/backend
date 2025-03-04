FROM openjdk:17-jdk-alpine
EXPOSE 8080

ENV ENVIRONMENT="local"
ENV JVM_OPTIONS=""

VOLUME /tmp
COPY ./target/*.jar medcom-backend.jar

CMD ["sh", "-c", "exec java ${JVM_OPTIONS} -Dspring.profiles.active=${ENVIRONMENT} -jar medcom-backend.jar"]