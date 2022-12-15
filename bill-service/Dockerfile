FROM openjdk:8-jdk-alpine
EXPOSE 8082
COPY build/libs/bill-service-1.0-SNAPSHOT.jar .
RUN apk add --no-cache bash
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
ENTRYPOINT ["/wait-for.sh", "account-service:8085", "--timeout=180", "--", "java", "-jar", "bill-service-1.0-SNAPSHOT.jar"]