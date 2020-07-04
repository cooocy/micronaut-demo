FROM openjdk:14-alpine
COPY target/home-*.jar home.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "home.jar"]