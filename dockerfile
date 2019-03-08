FROM myregistry.com/public/openjdk:8-slim

ADD ./SpringBootDemo.jar /
#EXPOSE 8080:80

CMD ["java", "-jar", "/SpringBootDemo.jar"]

