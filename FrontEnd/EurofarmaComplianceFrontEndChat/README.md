# Eurofarma Compliance Front End Chat

Este diretório contém os arquivos de front-end para o chatbot de compliance da Eurofarma.

## Estrutura do Diretório

- `index.html`: Arquivo principal contendo a estrutura HTML e configuração do chatbot.
- `imagens/`: Pasta contendo as imagens utilizadas na customização do chatbot.

## Descrição do Chatbot

O chatbot é integrado utilizando a biblioteca `flowise-embed` e está configurado para se conectar a um fluxo específico através do `chatflowid`. As configurações incluem customizações de tema para botões, janelas de chat, e mensagens tanto do bot quanto do usuário.

### Customizações Principais

- **Botão de Início do Chat:** Customizável em termos de cor, posição, tamanho, e ícone.
- **Janela do Chat:** Exibe um título, avatar, e uma mensagem de boas-vindas quando aberto.
- **Mensagens do Bot e Usuário:** Configure as cores de fundo, texto, e avatares para mensagens do bot e do usuário.
- **Campo de Input de Texto:** Inclui opções como placeholder, cor de fundo, cor do texto, e um botão de envio.
- **Footer:** Texto e link configuráveis.

## Como Utilizar

1. Clone o repositório:
    ```sh
    git clone https://github.com/SeuUsuario/ProjetoEuroFarma.git
    ```

2. Navegue até o diretório do front-end do chatbot:
    ```sh
    cd ProjetoEuroFarma/FrontEnd/EurofarmaComplianceFrontEndChat
    ```

3. Abra o arquivo `index.html` em seu navegador para visualizar e interagir com o chatbot.

4. Certifique-se de que o servidor de back-end está rodando e acessível no `apiHost` configurado no script do `index.html`.

## Dependências

Este front-end utiliza a versão embutida da biblioteca Flowise para facilitar a inclusão e customização do chatbot.
