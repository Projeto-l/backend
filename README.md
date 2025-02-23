# MedCom - Gerenciamento de Prescrições Médicas

## 📌 Visão Geral
MedCom é um **sistema de gerenciamento de prescrições médicas** desenvolvido para auxiliar pediatras no cálculo de 
dosagens, identificação de interações medicamentosas e geração eficiente de receitas. O sistema funciona c
omo uma **extensão de navegador** e integra-se com sistemas hospitalares.

## 🚀 Tecnologias Utilizadas
- **Backend:** Java, Spring Boot (Spring MVC, Spring Web, Spring Data JPA, Spring Security)
- **Banco de Dados:** PostgreSQL (via Docker) com Flyway para migrações
- **Frontend:** React, Chrome Extension API, WebExtensions API
- **Infraestrutura:** Docker, Docker Compose
- **Processamento de Linguagem Natural:** Python (para detecção de interações medicamentosas)
- **Documentação:** Swagger (OpenAPI 3)

## 🏗️ Estrutura do Projeto
```
medcom/
│── src/               # Backend em Java Spring Boot
│   ├── main/java/com/medcom
│   │── main/resouces/db/migration/          # Scripts SQL para migrações do banco
│   ├── test/
├── pom.xml            # Dependências Maven
├── application.yml    # Configuração do Spring Boot
│── docker-compose.yml     # Configuração do PostgreSQL para desenvolvimento
│── README.md              # Documentação do projeto
```

## ⚙️ Configuração do Projeto

### 📦 1. Executar PostgreSQL com Docker
Certifique-se de que o **Docker** está instalado e inicie o banco de dados:
```bash
docker-compose up -d
```

### 🚀 2. Executar o Backend
Acesse o diretório do backend e inicie a aplicação Spring Boot:
```bash
cd backend
mvn spring-boot:run
```

## 🔗 Documentação da API
Após iniciar o backend, acesse a documentação **Swagger UI**:
```
http://localhost:8080/swagger-ui.html
```

## 🛠️ Migrações do Banco de Dados (Flyway)
Para aplicar as migrações manualmente, execute:
```bash
mvn flyway:migrate
```

Para verificar o status das migrações:
```bash
mvn flyway:info
```

## 🛑 Parando a Aplicação
Para parar e remover o container do banco de dados:
```bash
docker-compose down -v
```

## 📌 Melhorias Futuras


## 👨‍💻 Contribuidores
- **Ana Paula Lima dos Santos** - Desenvolvedora Backend
- **Álex Micaela de Oliveira Fidelis** - Desenvolvedora Frontend
- **Guilherme Araújo Matos** - Desenvolvedor Backend
- **Juan Gabriel Gonçalves Dias** - Desenvolvedor Frontend
- **Luiz Carlos Abrantes Xavier** - Especialista em NLP
- **Welbber Vital Porto** - Scrum Master & Desenvolvedor Backend
