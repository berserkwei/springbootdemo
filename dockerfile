FROM myregistry.com/public/openjdk:8-slim

ADD target/SpringBootDemo.jar /
ADD test.yml /
#EXPOSE 8080:80
WORKDIR /
CMD ["java", "-jar", "/SpringBootDemo.jar"]

