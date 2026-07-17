# Этап 1: Сборка приложения
FROM gradle:8.7-jdk17 AS builder
WORKDIR /app
# Копируем всё содержимое текущей папки внутрь Докера
COPY . .
# Собираем проект
RUN gradle clean build -x test

# Этап 2: Запуск приложения
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]