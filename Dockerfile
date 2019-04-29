FROM java:8-jdk-alpine

COPY ./target/locadora-1.0.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch locadora-1.0.0.jar'

ENTRYPOINT ["java","-jar","locadora-1.0.0.jar"]