# Usa a imagem oficial do JDK 17 como base
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR do build para o container
COPY target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Define o comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
