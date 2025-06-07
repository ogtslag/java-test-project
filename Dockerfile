# --- STAGE 1: Build the application ---
# Usamos una imagen base que incluye Java y herramientas de construcción (Maven o Gradle)
# Se recomienda usar una versión específica de Java (ej. openjdk:17-jdk-slim)
FROM openjdk:17-jdk-slim AS build

RUN apt-get update && \
    apt-get install -y maven


# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración de Maven/Gradle y el archivo de proyecto
# Esto permite que Docker cachee las dependencias si el pom.xml/build.gradle no cambia
COPY pom.xml .
# Si usas Gradle: COPY build.gradle settings.gradle ./
# Si tienes sub-módulos o multi-módulos, copia los archivos de cada uno
# COPY your-module-name/pom.xml your-module-name/

# Descarga las dependencias del proyecto (solo si el pom.xml/build.gradle ha cambiado)
# Esto optimiza el caché de Docker
RUN mvn dependency:go-offline


# Copia el código fuente del proyecto
COPY src ./src

# Construye el proyecto y genera el JAR ejecutable
# El comando clean package skipTests construye el JAR y omite las pruebas
RUN mvn clean package -DskipTests
# Si usas Gradle: RUN gradle build --no-daemon -x test

# --- STAGE 2: Create the final image ---
# Usamos una imagen base más ligera para la aplicación final para reducir el tamaño de la imagen
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado desde la etapa de construcción
# Asegúrate de que el nombre del JAR sea correcto.
# Puedes verificar el nombre en la carpeta 'target' después de compilar localmente
# O puedes usar un patrón para asegurar que se copie el JAR generado
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar

# Expone el puerto en el que la aplicación Spring Boot se ejecutará (por defecto 8080)
EXPOSE 8001

# Define el comando para ejecutar la aplicación cuando el contenedor se inicie
ENTRYPOINT ["java", "-jar", "app.jar"]

# Opcional: Define un usuario no root para ejecutar la aplicación por seguridad
# RUN addgroup --system spring && adduser --system --ingroup spring spring
# USER spring