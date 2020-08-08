FROM openjdk:14-alpine

ENV JAVA_OPTS="-server -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -Xms384M -Xmx1024M"
ENV TZ Europe/Warsaw

EXPOSE 8080

COPY build/libs/webflux-log-appender-1.jar webflux-log-appender-1.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-XX:+UnlockExperimentalVMOptions","-jar","webflux-log-appender-1.jar"]