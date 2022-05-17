FROM java:8
COPY . /var/www/java
WORKDIR /var/www/java
RUN javac WebServer.java
EXPOSE 6969
CMD ["java", "WebServer", "6969", "."]