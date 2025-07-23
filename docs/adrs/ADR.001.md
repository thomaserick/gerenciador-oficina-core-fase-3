# Estruturação de pacotes
 
**Data:** 22/07/2025
**Situação:** Aceita
 
## Contexto
 
Estruturar os pacotes como base a segregação de responsabilidades, separando também a gestão de configuração e do produto em si.
 
## Decisão
 
Sendo assim a estrutura básica ficou estabelecida da seguinte forma:
 
```java
. giq
  . plano            // domínio.
  . amostragem       // domínio.
  . fornecedor       // domínio.
  . ...              // outros domínios.
  . sk               // código compartilhado entre os demais pacotes; deve conter, preferencialmente, identificadores e valores de objetos.
. infra              // código de configuração de recursos técnicos que não fazem parte da implementação de negócio em si, como por exemplo configuração de bibliotecas parceiras.
```
 
Desconsiderando o pacote de `infraestrutura` (`infra`) e `shared kernel` (`sk`), a estruturação do pacote, para o **domínio**, deve seguir o seguinte modelo:
 
```java
. domain: plano | amostragem | fornecedor | demais - domínios
  . adapter          // conforme definido na arquitetura hexagonal devemos mapear os adaptadores de entrada e saídas.
    . in             // adaptadores de entrada.
      . api          // definição dos adaptadores Restful para fornecimento das APIs (_controllers_).
      . stream       // definição dos adaptadores de entrada para a mensageria (_subscribers_).
    . out            // adaptadores de saída.
      . stream       // definição dos adaptadores de saída para a mensageria (_publishers_).
      . db           // implementação dos contratos de acesso ao banco de dados.
  . app              // definição dos serviços de aplicação; cada caso de uso deve possuir o seu. 
  . domain            // implementação do código de domínio.
    . ...            // pode conter sub-divisões caso necessários; não pode conter código de infraestrutura.
  . repository       // definição do contrato de repositório para o domínio.
  . .                // definição dos casos de usos a serem implementados.
```

Desconsiderando o pacote de `infraestrutura` (`infra`) e `shared kernel` (`sk`), a estruturação do pacote, para as **consultas**, deve seguir o seguinte modelo:
 
```java
. domain: plano | amostragem | fornecedor | demais
  . adapter          // conforme definido na arquitetura hexagonal devemos mapear os adaptadores de entrada e saídas.
    . in             // adaptadores de entrada.
      . api          // definição dos adaptadores Restful para fornecimento das APIs (_controllers_).
        . criteria   // definição dos critérios de consulta, ordenação e paginação por exemplo.
        . projection // definição das interfaces de representação das projeções para as consultas.
      . stream       // definição dos adaptadores de entrada para a mensageria (_subscribers_).
    . out            // adaptadores de saída.
      . stream       // definição dos adaptadores de saída para a mensageria (_publishers_).
      . db           // implementação dos contratos de acesso ao banco de dados.
  . app              // definição dos serviços de aplicação.
  . domain           // implementação do código com a representação das tabelas.
```

## Consequências
 
Em determinado casos pode ser que alguns pacotes fiquem com um número grande de classes; caso isso venha a acontecer é prudente agrupar por contexto/domínio para facilitar o entendimento.
