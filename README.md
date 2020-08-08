# webflux-log-appender

## How to run:
### use docker:
 - run docker image: `docker run -p 8080:8080/tcp kamilforemny/webflux-log-appender`
 - register flux and listen on events: `curl -H 'Accept: text/event-stream' localhost:8080/events`
 - create sample event: `curl localhost:8080/test`

### or: 
 - download repo
 - run project
 - register flux and listen on events: `curl -H 'Accept: text/event-stream' localhost:8080/events`
 - create sample event: `curl localhost:8080/test`

