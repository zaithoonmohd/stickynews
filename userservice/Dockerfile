From java:8-jre
WORKDIR usr/src
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_UST_URL=jdbc:mysql://localhost:3306/newsdb?createDatabaseIfNotExist=true&user=root&password=root&verifyServerCertificate=false&useSSL=false&requireSSL=false&allowPublicKeyRetrieval=true
ADD ./target/userservice-1.0.jar usr/src/userservice-1.0.jar
EXPOSE 9096
ENTRYPOINT ["java","-jar","usr/src/userservice-1.0.jar"]