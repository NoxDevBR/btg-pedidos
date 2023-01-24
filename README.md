# BTG Pedidos

Estão inclusos neste repositório duas aplicações microsserviços:

- **pedido-consumer**: Responsável por consumir os dados da fila RabbitMQ `pedido.created` e persistir no banco.
- **pedido-api**: API Rest para consulta de dados de pedidos.

## Ambiente
Para executar a aplicação localmente será necessário:
- Java 17
- MongoDB
- RabbitMQ

Está incluso no repositório um arquivo `docker-compose.yml` contendo imagens para o RabbitMQ, MongoDB e Mongo Express.

## Execução

As configurações locais de cada aplicação podem ser alteradas no `application.yml` de cada uma das aplicações.

Executar as aplicações utilizando o comando `./gradlew bootRun` ou através de uma IDE (exemplo: IntelliJ). 
Os testes unitários podem ser executados com `./gradlew test`. 

A aplicação de API possui um swagger que pode ser utilizado para testar os endpoints. Por padrão pode ser acessado em `localhost:8080/swagger-ui/`.

Para gerar as mensagens na fila RabbitMQ, é recomendado utilizar o RabbitMQ Management (incluso no ambiente docker-compose). Acessar através da url padrão `localhost:15672/` usuário e senha padrão `guest:guest`. Acessar a tela `Queues > pedido.created > Publish Message` e criar a mensagem com payload seguindo o contrato abaixo:

> Obs: Caso a fila não exista, ela será criada automaticamente pela aplicação de consumer.

## Fila *pedido.created*
```json
{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 5.99
    }
  ]
}
```