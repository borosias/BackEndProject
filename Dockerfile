FROM openjdk:17
RUN mkdir app
RUN cd app
WORKDIR /app
COPY target/SpringDevKpi-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","SpringDevKpi-0.0.1-SNAPSHOT.jar"]