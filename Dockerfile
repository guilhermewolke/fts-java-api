FROM maven:3.8.2-openjdk-17
LABEL authors="guilherme_wolke@yahoo.com"
WORKDIR /app
COPY /api .
RUN mvn clean install -e
#ENTRYPOINT ["tail","-f","/dev/null"]
CMD mvn spring-boot:run