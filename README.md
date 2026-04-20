# API de Catálogo de Adoção de Pets

Este projeto consiste em uma API RESTful para gerenciar o cadastro de animais para adoção, utilizando **Java com Spring Boot** e **MongoDB** como banco de dados. O objetivo principal é demonstrar conhecimento em bancos de dados NoSQL, criação de APIs RESTful, tratamento de exceções e arquitetura de software, sendo um excelente projeto para portfólio.

## 🚀 Tecnologias Utilizadas

- **Java 11+**
- **Spring Boot 2.7.x**: Framework para construção de aplicações Java robustas.
- **Spring Data MongoDB**: Integração com o banco de dados MongoDB.
- **MongoDB**: Banco de dados NoSQL para armazenamento flexível de dados de pets.
- **Lombok**: Reduz a verbosidade do código Java com anotações.
- **Spring Boot Starter Validation**: Validação de dados de entrada.
- **Springdoc OpenAPI UI (Swagger)**: Geração automática de documentação da API e interface para testes.

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida para garantir a separação de responsabilidades e facilitar a manutenção:

- **Controller**: Responsável por receber as requisições HTTP (endpoints) e retornar as respostas.
- **Service**: Contém a lógica de negócio da aplicação, orquestrando as operações e aplicando regras de negócio.
- **Repository**: Interface para interação direta com o banco de dados MongoDB, utilizando o Spring Data MongoDB.
- **Model/Entity**: Representa a estrutura dos dados (entidades) que serão armazenados no MongoDB.

## ✨ Funcionalidades (MVP)

As funcionalidades principais implementadas nesta API incluem:

- **Cadastro Flexível de Pets**: Aproveita a flexibilidade do MongoDB para armazenar características variadas para diferentes espécies de pets (ex: `porte` para cachorros, `envergaduraAsas` para pássaros).
- **Sistema de Filtros Avançado**: Endpoint para buscar pets por `espécie`, `idade` ou `cidade`.
- **Status de Adoção**: Gerenciamento do status de adoção de um pet (`DISPONIVEL`, `EM_PROCESSO`, `ADOTADO`).
- **Upload de Imagens (URL)**: Campo para armazenar a URL da imagem do pet.
- **Documentação com Swagger (OpenAPI)**: Interface interativa para explorar e testar os endpoints da API.
- **Validação de Dados**: Utilização do Bean Validation do Spring para garantir a integridade dos dados (ex: nome não vazio, idade não negativa).
- **Tratamento de Erros Customizado**: Respostas de erro amigáveis e informativas para o cliente da API.

## ⚙️ Como Rodar o Projeto

### Pré-requisitos

- **Java Development Kit (JDK) 11 ou superior**
- **Maven**
- **MongoDB**: Certifique-se de ter uma instância do MongoDB rodando localmente na porta padrão (27017) ou configure a URI de conexão no arquivo `src/main/resources/application.properties`.

### Passos

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/pet-adoption-api.git
   cd pet-adoption-api
   ```
   *(Nota: Este é um exemplo. Você precisará criar seu próprio repositório no GitHub e fazer o upload do código.)*

2. **Compile o projeto com Maven:**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação Spring Boot:**
   ```bash
   mvn spring-boot:run
   ```

A aplicação estará disponível em `http://localhost:8080`.

## 📖 Documentação da API (Swagger UI)

Após iniciar a aplicação, você pode acessar a documentação interativa da API através do Swagger UI no seguinte endereço:

`http://localhost:8080/swagger-ui.html`

Nesta interface, você poderá visualizar todos os endpoints disponíveis, seus parâmetros de entrada e saída, e testá-los diretamente no navegador.

## 🛠️ Endpoints Principais

| Método HTTP | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/api/pets` | Lista todos os pets disponíveis para adoção. |
| `GET` | `/api/pets/{id}` | Busca um pet específico pelo seu ID. |
| `POST` | `/api/pets` | Cadastra um novo pet para adoção. |
| `PUT` | `/api/pets/{id}` | Atualiza os dados de um pet existente. |
| `DELETE` | `/api/pets/{id}` | Exclui um pet do catálogo. |
| `GET` | `/api/pets/search` | Busca pets com filtros avançados (espécie, idade, cidade). |
| `PATCH` | `/api/pets/{id}/status` | Atualiza o status de adoção de um pet. |

### Exemplo de Payload para Cadastro (POST /api/pets)

```json
{
  "name": "Rex",
  "species": "Cachorro",
  "age": 3,
  "city": "São Paulo",
  "imageUrl": "https://exemplo.com/imagem-rex.jpg",
  "adoptionStatus": "DISPONIVEL",
  "characteristics": {
    "porte": "Médio",
    "pelagem": "Curta",
    "vacinado": true
  }
}
```

## 🤝 Contribuição

Sinta-se à vontade para contribuir com este projeto! Você pode abrir *issues* para relatar bugs ou sugerir novas funcionalidades, e enviar *pull requests* com melhorias no código.

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
