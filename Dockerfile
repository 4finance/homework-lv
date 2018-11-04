FROM gradle:jdk10 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM openjdk:10-jre-slim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar homework.jar
ENTRYPOINT exec java -jar /homework.jar