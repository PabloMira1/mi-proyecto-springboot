# Usa una imagen de base de Java
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR en el contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que usará la aplicación
EXPOSE 8081

# Comando para ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
