FROM alpine:3.14
RUN apk add --no-cache openjdk23
COPY target  /app/
