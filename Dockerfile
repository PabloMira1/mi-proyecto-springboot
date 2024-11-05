# Usar una imagen base de Java
FROM openjdk:17-jdk-alpine
# Establecer el directorio de trabajo
WORKDIR /app
# Copiar el archivo JAR de tu aplicación al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
# Exponer el puerto en el que la aplicación escuchará
EXPOSE 8081
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
