From java:8-jre
WORKDIR usr/src
ENV MONGO_UST_URL=mongodb://localhost:27017/mongonews
ADD ./target/favouriteservice-1.0.jar usr/src/favouriteservice-1.0.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","usr/src/favouriteservice-1.0.jar"]