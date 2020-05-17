# Dockerfile
FROM openjdk:8-jdk
ADD target/*.jar stocks-market-job.jar
CMD java $JAVA_OPTS -jar /stocks-market-job.jar
