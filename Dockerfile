FROM java:8-jdk

ADD build/libs/composers-unicorn-swarm.jar /composers-unicorn.jar

EXPOSE 8080
RUN bash -c 'touch /composers-unicorn.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/composers-unicorn.jar"]