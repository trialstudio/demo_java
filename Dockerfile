FROM eclipse-temurin:17-jre
COPY target/*.jar /root/app.jar
CMD ["java", "-jar", "/root/app.jar"]