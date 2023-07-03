# Trabalho final da cadeira de PW2 e VVS - 2023/01 - IFRS

# Accounts Manager - para cuidar dos gastos mensais de forma mais prática que com planilhas.

## Plano de testes
Como a aplicação *não possui front-end desenvolvido nesse primeiro momento*, os seguintes requisitos do projeto final da disciplina foram implementados:
- [x] Escrever um **plano de teste**, ou seja, um documento que descreva cada teste juntamente com as entradas e saídas esperadas. 
- [x] Implementar uma **verificação estática** no projeto (**PMD** e/ou **SonarClould** - foi instalado o PMD no projeto e utilizado SonarLint no VS Code)
- [x] Implementar um **conjunto de testes unitários**
- [ ] Implementar **testes de componentes (API)** <s>e/ou testes de sistema (Selenium ou Cypress)</s>
- [x] Configure um ambiente de integração contínua de sua escolha, por exemplo, **Github Actions**, Jekins, Travis, Circle-CI, GitLab, entre outros.

### Verificação estática
A verificação estática foi feita através da adição do plugin e da extensão do PMD juntamente com a adição da extenção do Sonarlint ao projeto no VS Code. Além disso, foi seguido o passo-a-passo para a implementação do Github Actions.

### Testes Unitários
Pasta de testes contém testes para os métodos básicos das classes, verificando se os mesmos funcionam confirme esperado.

### Testes de Componente
Em desenvolvimento

### Github Actions CI/CD
Foram implementadas actions de build com Java CI com Maven e Revisão de dependências.
