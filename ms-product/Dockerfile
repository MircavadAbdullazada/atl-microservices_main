FROM openjdk:17-jdk-alpine
COPY ./build/libs/product-0.0.1-SNAPSHOT.jar product.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/product.jar"]