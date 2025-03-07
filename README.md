# bmuliterno-java-challenge

# VirtualStore Application

Este é um projeto stateless simples para gerenciar produtos, categorias e carrinhos de compras. O aplicativo foi desenvolvido em Spring Boot e pode ser executado localmente usando Docker.

---

## Pré-requisitos

Certifique-se de que você possui os seguintes itens instalados em sua máquina antes de começar:

- **Docker**: [Instalar Docker](https://www.docker.com/)
- **Docker Compose** (opcional): [Instalar Docker Compose](https://docs.docker.com/compose/)

---

## Como Executar o Aplicativo com Docker

1. **Clonar o repositório**

   Clone este repositório para sua máquina local:

   ```bash
   git clone https://github.com/bmuliterno/bmuliterno-java-challenge.git

2. **Gerar o arquivo JAR**

Certifique-se de ter o Maven instalado e gere o JAR do projeto:
   
`mvn clean package`

O arquivo gerado estará localizado no diretório target/ e deve ser algo como VirtualStore-0.0.1-SNAPSHOT.jar.

3. **Construir a imagem Docker**

Use o arquivo Dockerfile para criar a imagem Docker do aplicativo:

`
docker build -t virtualstore-app .`

4. **Executar o container Docker**

Depois de construir a imagem, execute o seguinte comando para iniciar o container:

`docker run -p 8080:8080 virtualstore-app`

Agora, o aplicativo estará rodando em http://localhost:8080.

## Usando Docker Compose (Opcional)
Se você preferir usar o Docker Compose, siga as etapas abaixo:

1. **Criar o arquivo `docker-compose.yml`**

Certifique-se de que o arquivo `docker-compose.yml` está na raiz do projeto.

2. **Iniciar os serviços**

Execute o seguinte comando para iniciar o aplicativo:

`docker-compose up`

O aplicativo estará acessível em http://localhost:8080.

3. **Parar os serviços**

Para parar os serviços em execução, utilize o comando:

`docker-compose down`

## Build

`mvn clean install`

## Testando a API
Você pode testar os endpoints usando ferramentas como:

**Swagger UI**: Após iniciar a aplicação, acesse a rota:

`http://localhost:8080/swagger-ui/index.html#/`

**Postman**: Crie requisições para os endpoints documentados.

**curl**: Use comandos para testar os endpoints. Por exemplo:

`curl http://localhost:8080/produtos`

## Exemplos de Endpoints
**Cadastrar Produto** (POST /produtos)

**Listar Produtos** (GET /produtos)

**Associar Produto a Categoria** (POST /produtos/{productId}/categoria/{categoryId})