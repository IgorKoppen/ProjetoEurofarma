# Flowise - Build LLM Apps Easily

Flowise é uma plataforma de arrastar e soltar para construir fluxos personalizados de LLM (Language Model). Ela facilita a criação de aplicações baseadas em modelos de linguagem, permitindo integração de várias fontes de dados e APIs.

Este projeto foi utilizado no desenvolvimento de fluxos dos chatbots de compliance e de dados da Eurofarma.

## ⚡ Início Rápido

### Instalação e Execução

1. **Instalar o Flowise**
    ```bash
    npm install -g flowise
    ```
2. **Iniciar o Flowise**
    ```bash
    npx flowise start
    ```
    Com nome de usuário e senha:
    ```bash
    npx flowise start --FLOWISE_USERNAME=usuario --FLOWISE_PASSWORD=1234
    ```
3. **Acessar a Aplicação**
    Abra [http://localhost:3000](http://localhost:3000).

## 🐳 Docker

### Docker Compose

1. Vá para a pasta `docker` no diretório raiz do projeto.
2. Copie o arquivo `.env.example`, cole na mesma localização e renomeie para `.env`.
3. Execute:
    ```bash
    docker compose up -d
    ```
4. Abra [http://localhost:3000](http://localhost:3000).
5. Para parar os containers:
    ```bash
    docker compose stop
    ```

### Docker Image

1. Construa a imagem localmente:
    ```bash
    docker build --no-cache -t flowise .
    ```
2. Execute a imagem:
    ```bash
    docker run -d --name flowise -p 3000:3000 flowise
    ```
3. Pare a imagem:
    ```bash
    docker stop flowise
    ```

## 👨‍💻 Desenvolvimento

O Flowise possui 3 módulos diferentes em um único repositório monolítico:

- `server`: Backend Node.js para servir lógicas de API.
- `ui`: Frontend em React.
- `components`: Integrações com nós de terceiros.

### Pré-requisitos

- Instalar [PNPM](https://pnpm.io/installation)
    ```bash
    npm i -g pnpm
    ```

### Configuração

1. Clone o repositório:
    ```bash
    git clone https://github.com/FlowiseAI/Flowise.git
    ```
2. Entre na pasta do repositório:
    ```bash
    cd Flowise
    ```
3. Instale todas as dependências de todos os módulos:
    ```bash
    pnpm install
    ```
4. Construa todo o código:
    ```bash
    pnpm build
    ```
5. Inicie a aplicação:
    ```bash
    pnpm start
    ```
   Acesse a aplicação em [http://localhost:3000](http://localhost:3000).

Para o build de desenvolvimento:

- Crie um arquivo `.env` e especifique `VITE_PORT` em `packages/ui`.
- Crie um arquivo `.env` e especifique `PORT` em `packages/server`.
- Execute:
    ```bash
    pnpm dev
    ```

Qualquer mudança no código recarregará automaticamente a aplicação em [http://localhost:8080](http://localhost:8080).

## 🔒 Autenticação

Para habilitar a autenticação a nível de aplicativo, adicione `FLOWISE_USERNAME` e `FLOWISE_PASSWORD` ao arquivo `.env` em `packages/server`:

```
FLOWISE_USERNAME=usuario
FLOWISE_PASSWORD=1234
```

## 🌱 Variáveis de Ambiente

Flowise suporta diferentes variáveis de ambiente para configurar sua instância. Você pode especificar as variáveis no arquivo `.env` dentro da pasta `packages/server`.

## 📖 Documentação

Confira a documentação completa em [Flowise Docs](https://docs.flowiseai.com/).

## 🌐 Auto-Hospedagem

Implante o Flowise auto-hospedado em sua infraestrutura existente. Suporte para várias [opções de deployment](https://docs.flowiseai.com/configuration/deployment):

- [AWS](https://docs.flowiseai.com/deployment/aws)
- [Azure](https://docs.flowiseai.com/deployment/azure)
- [Digital Ocean](https://docs.flowiseai.com/deployment/digital-ocean)
- [GCP](https://docs.flowiseai.com/deployment/gcp)
- [Alibaba Cloud](https://computenest.console.aliyun.com/service/instance/create/default?type=user&ServiceName=Flowise社区版)

## ☁️ Flowise Cloud

Comece com o [Flowise Cloud](https://flowiseai.com/).

## 🙋 Suporte

Sinta-se à vontade para fazer perguntas, relatar problemas e solicitar novos recursos na [discussão](https://github.com/FlowiseAI/Flowise/discussions).

## 🙌 Contribuição

Agradecimentos aos incríveis contribuidores do projeto:
<a href="https://github.com/FlowiseAI/Flowise/graphs/contributors">
<img src="https://contrib.rocks/image?repo=FlowiseAI/Flowise" />
</a>

Veja o [guia de contribuição](CONTRIBUTING.md). Entre em contato conosco no [Discord](https://discord.gg/jbaHfsRVBW) se tiver dúvidas ou problemas.

## 📄 Licença

O código fonte deste repositório está disponível sob a [Licença Apache Versão 2.0](LICENSE.md).
