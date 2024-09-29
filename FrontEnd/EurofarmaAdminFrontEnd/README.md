# Eurofarma Admin Front End

Este projeto é a interface de administração da Eurofarma desenvolvido em Angular. Fornece funcionalidades para gerenciar várias entidades e dados relacionados à empresa.

## Estrutura de Pastas

- **Errors**: Contém arquivos relacionados ao tratamento de erros.
- **components**: Components do projeto.
- **guards**: Guards de rotas para segurança e autorização.
- **interfaces**: Interfaces TypeScript utilizadas pelo projeto.
- **pages**: Páginas específicas do aplicativo.
- **pipe**: Pipes usados no projeto.
- **services**: Serviços Angular para interação com APIs e outras tarefas.
- **util**: Funções utilitárias e helpers.

## Dependências

As principais dependências e suas versões estão listadas abaixo:

### Dependências do Projeto

- `@angular/animations`: ^17.0.0
- `@angular/cdk`: ^17.3.10
- `@angular/common`: ^17.0.0
- `@angular/compiler`: ^17.0.0
- `@angular/core`: ^17.0.0
- `@angular/forms`: ^17.0.0
- `@angular/material`: ^17.3.10
- `@angular/platform-browser`: ^17.0.0
- `@angular/platform-browser-dynamic`: ^17.0.0
- `@angular/platform-server`: ^17.0.0
- `@angular/router`: ^17.0.0
- `@angular/ssr`: ^17.0.7
- `express`: ^4.18.2
- `jspdf`: ^2.5.2
- `jspdf-autotable`: ^3.8.3
- `rxjs`: ~7.8.0
- `tslib`: ^2.3.0
- `zone.js`: ~0.14.2

### Dependências de Desenvolvimento

- `@angular-devkit/build-angular`: ^17.0.7
- `@angular/cli`: ^17.0.7
- `@angular/compiler-cli`: ^17.0.0
- `@types/express`: ^4.17.17
- `@types/jasmine`: ~5.1.0
- `@types/node`: ^18.18.0
- `jasmine-core`: ~5.1.0
- `karma`: ~6.4.0
- `karma-chrome-launcher`: ~3.2.0
- `karma-coverage`: ~2.2.0
- `karma-jasmine`: ~5.1.0
- `karma-jasmine-html-reporter`: ~2.1.0
- `typescript`: ~5.2.2

## Scripts Disponíveis

Os scripts definidos no arquivo `package.json` são:

- `ng`: Comando para acionar o CLI Angular.
- `start`: Inicia o servidor de desenvolvimento.
- `build`: Compila a aplicação.
- `watch`: Compila continuamente em modo de desenvolvimento.
- `test`: Executa os testes unitários.
- `serve:ssr:EurofarmaAdminFrontEnd`: Inicia o servidor com renderização no servidor (SSR).

## Como Rodar o Projeto

1. **Clonar o repositório**:
   ```sh
   git clone https://github.com/SeuUsuario/ProjetoEuroFarma.git
   ```

2. **Instalar as dependências**:
   ```sh
   cd ProjetoEuroFarma/FrontEnd/EurofarmaAdminFrontEnd
   npm install
   ```

3. **Iniciar o servidor de desenvolvimento**:
   ```sh
   npm start
   ```

4. **Build da aplicação**:
   ```sh
   npm run build
   ```

5. **Executar testes**:
   ```sh
   npm test
   ```
