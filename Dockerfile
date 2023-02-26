FROM bellsoft/liberica-openjdk-alpine-musl
EXPOSE 8002
COPY ./target/StockService-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","StockService-0.0.1-SNAPSHOT.jar"]