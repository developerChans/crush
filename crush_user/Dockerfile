FROM openjdk:17-jre-slim

# 작업 디렉터리 설정
WORKDIR /app

# Jar 파일 복사
COPY build/libs/user-service.jar /app/app.jar

# MySQL 설치 및 설정
RUN apt-get update && apt-get install -y mysql-server && \
    service mysql start && \
    mysql -e "CREATE DATABASE userdb;" && \
    mysql -e "CREATE USER 'root'@'%' IDENTIFIED BY 'password';" && \
    mysql -e "GRANT ALL PRIVILEGES ON userdb.* TO 'root'@'%';" && \
    mysql -e "FLUSH PRIVILEGES;"

# MySQL 초기화 스크립트 복사
COPY init-user-db.sql /docker-entrypoint-initdb.d/

# 컨테이너 실행 시 명령
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the build artifacts from the host machine to the container
COPY build/libs/*.jar app.jar

# Set the entry point to run the application
ENTRYPOINT ["java","-jar","/app.jar"]
