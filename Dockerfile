# Usa una imagen de Maven con Java preinstalado
FROM maven:3.8.6-openjdk-17-slim as builder

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y las dependencias de la aplicación
COPY pom.xml ./
COPY src ./src

# Compila la aplicación
RUN mvn clean package -DskipTests

# Usar una imagen más ligera para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Copia el archivo JAR desde la etapa de construcción
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
