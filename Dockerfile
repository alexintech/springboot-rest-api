# BUILD CONTAINER
FROM openjdk:8-jdk-alpine AS gradle-host

RUN mkdir /incoming
WORKDIR /incoming

# Download dependencies
ADD *.gradle gradlew /incoming/
ADD gradle/ /incoming/gradle
RUN ./gradlew build -x :bootRepackage -x test --continue --stacktrace

# Copy the source code in and build it
ADD src/ /incoming/src
RUN ./gradlew buildForContainer --stacktrace



# RUNTIME CONTAINER
FROM openjdk:8-jre-alpine

# Copy the product
COPY --from=gradle-host /incoming/build/output/ /srv/
WORKDIR /srv

# Run the built product when the container launches
EXPOSE 8080
ENTRYPOINT ["java","-jar","/srv/app.jar"]