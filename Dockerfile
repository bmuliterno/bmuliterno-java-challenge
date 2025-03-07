# Use a imagem oficial do OpenJDK como base
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o arquivo JAR gerado para o diretório de trabalho no container
COPY target/virtual-store-1.0-SNAPSHOT.jar app.jar

# Exponha a porta onde o Spring Boot roda por padrão
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
