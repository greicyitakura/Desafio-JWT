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


# Como funciona o JWT
  
🤯[Entendimento do JWT](https://github.com/greicyitakura/Desafio-JWT/blob/master/jwt-fluxo.drawio.png)

# Tecnologias utilizadas
- Java
- Spring Boot
- JUnit

# Estrutura do Projeto

- Domain: Contém as classes de domínio do projeto
- Service: Implementa a lógica de criação e validação do JWT
- Controller: Responsável pelas chamadas da API
- Utils: Implementa a validação de cada regra do desafio

# 💻 Como executar o projeto

Pré-requisitos:

Java 17

Utilizar uma IDE com compatível com Java


```bash
# Para clonar o repositório
git clone https://github.com/greicyitakura/desafioJWT

# Para executar o projeto
./mvnw spring-boot:run
```

<h2 id="routes">📍 API Endpoints</h2>

API disponível http://localhost:8080

ou

API disponível https://abrir.link/eMojw

# Faça as requisições via Postman importando a collections

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
