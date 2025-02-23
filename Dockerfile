# Stage 1 - the build stage
FROM gradle:8.5-jdk21-alpine as build
WORKDIR /app
COPY src src
COPY build.gradle build.gradle
RUN gradle clean build -x test


# Stage 2 - the production stage
FROM eclipse-temurin:21-alpine
RUN apk update && apk add curl
ENV TZ=Asia/Tashkent
WORKDIR /app
COPY --from=build /app/build/libs/app.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar","--enable-preview", "app.jar"]
HEALTHCHECK --interval=25s --timeout=3s --retries=2 CMD wget --spider http://localhost:5000/management/health || exit 1