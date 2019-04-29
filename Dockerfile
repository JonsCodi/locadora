FROM java:8-jdk-alpine

COPY ./target/locadora-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch locadora-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","locadora-0.0.1-SNAPSHOT.jar"]