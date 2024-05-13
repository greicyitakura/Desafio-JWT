# Desafio JWT
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)


# Sobre o desafio
Acesse a página 
[backend-challenge](https://github.com/99h58f2qe/backend-challenge)

O desafio consistia em construir uma aplicação que exponha uma api web que recebe por parametros um JWT (string) e verificar se é valida conforme regras abaixo:

- Deve ser um JWT válido
- Deve conter apenas 3 claims (Name, Role e Seed)
- A claim Name não pode ter carácter de números
- A claim Role deve conter apenas 1 dos três valores (Admin, Member e External)
- A claim Seed deve ser um número primo.
- O tamanho máximo da claim Name é de 256 caracteres.

O desafio tem como objetivo final fornecer um serviço que receba tokens JWT como entrada e valide se estão de acordo com um conjunto específico de regras de segurança, como a presença de claims obrigatórias e a verificação de determinadas condições nas claims. Isso é fundamental para garantir a integridade e a segurança das comunicações entre clientes e servidores em um ambiente distribuído.

# Como funciona o JWT

O JWT (JSON Web Token) é um padrão aberto (RFC 7519) que define uma maneira compacta e autônoma de transmitir informações de forma segura entre partes como um objeto JSON. Ele é frequentemente usado para autenticação e autorização em aplicativos da web e APIs RESTful devido à sua capacidade de transmitir informações de forma segura entre diferentes sistemas.
JWT desempenha um papel crucial na autenticação e autorização de APIs, fornecendo um método seguro e eficiente para validar a identidade de um usuário e conceder acesso a recursos protegidos. Ao gerar tokens assinados digitalmente, as aplicações podem verificar a autenticidade e integridade dos dados transmitidos, garantindo que apenas usuários autorizados possam acessar os serviços protegidos.
  
🤯[Entendimento do JWT](https://github.com/greicyitakura/Desafio-JWT/blob/master/jwt-fluxo.drawio.png)

# Tecnologias utilizadas
- Java
- Spring Boot
- JUnit

# Estrutura do Projeto

- Domain: Contém a classe de domínio do projeto
- Service: Implementa a lógica de criação e validação do JWT
- Controller: Responsável pelas chamadas da API
- Utils: Implementa a validação de cada regra do desafio

# 💻 Como executar o projeto

Pré-requisitos:

Java 17

Docker

Utilizar uma IDE com compatível com Java

<h2 id="routes">📍 API Endpoints</h2>

API disponível http://localhost:8080

```bash
# Para clonar o repositório
git clone https://github.com/greicyitakura/Desafio-JWT.git

# Para instalar as dependências usando Maven
mvn install

# Para executar o projeto
./mvnw spring-boot:run
```

 ou

 API disponível http://52.90.142.252/api/v1

```bash
#Construir a imagem Docker
docker build -t <nome_da_imagem> .

# Executar o contêiner Docker
docker run -d -p <porta_do_host>:<porta_do_container> --name <nome_do_contêiner> <nome_da_imagem>

# Parar o contêiner Docker
docker stop <nome_do_contêiner>
```


# Faça as requisições via Postman importando as collections

<h3 id="post-auth-detail">POST /jwt-validate</h3>

**REQUEST**
```json
{
    "JwtWebToken": "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"
}
```

**RESPONSE**
```json
{
   verdadeiro
}
```



# Autor
Greicy Itakura

https://www.linkedin.com/in/greicy-itakura
