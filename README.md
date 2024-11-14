# Sistema de Gerenciamento de Biblioteca

## Descrição
Este é um projeto de sistema de gerenciamento de biblioteca desenvolvido em Java. O sistema permite gerenciar itens, como livros e revistas, e realizar operações de empréstimo e devolução. Existem dois tipos de usuários no sistema: **Membro** , **Bibliotecário** e **Administrador**. O bibliotecário possui permissões para adicionar ou remover itens do acervo e Administrador pode realizar ações de editar e exluir usuário.

O sistema utiliza princípios de Programação Orientada a Objetos (POO) e permite a persistência de dados por meio de arquivos CSV (`membros.csv` , `itens.csv` e `administrador.csv`).

## Funcionalidades
- Cadastro de usuários (Bibliotecário ou Membro) e login com autenticação.
- Gerenciamento de itens: adição, remoção, listagem, empréstimo e devolução.
- Persistência de dados: os dados dos membros e itens são armazenados em arquivos CSV, permitindo carregá-los ao iniciar o sistema.

## Estrutura do Projeto
- `BibliotecaGUI.java`: Interface gráfica para o sistema, com tela de login, registro e menu principal.
- `Biblioteca.java`: Classe singleton que gerencia o acervo de itens e a lista de membros.
- `Membro.java`: Classe base para todos os usuários, com atributos como `idMembro`, `nome`, `login`, `senha` e `tipo`.
- `Bibliotecario.java`: Classe que herda de `Membro`, com permissões especiais para gerenciar itens.
- `Administrador.java`: Classe que herda de `Membro`, com permissões especiais para gerenciar usuários.
- `Item.java`: Classe abstrata que representa um item da biblioteca, como livro ou revista.
- `Livro.java` e `Revista.java`: Classes que herdam de `Item`, representando itens específicos.
- `ItemIndisponivelException.java`: Exceção personalizada para indicar indisponibilidade de itens para empréstimo.

### Arquivos CSV
- `membros.csv`: Armazena dados dos usuários (tipo, ID, nome, tipo, login e senha).
- `itens.csv`: Armazena dados dos itens do acervo (tipo, ID, título, ano de publicação e outros atributos).
- `administrador.csv`: Armazena dados dos usuários (login e senha).

#### Estrutura dos Arquivos CSV
**membros.csv**
Tipo, ID, Nome, Endereco, Login, Senha 
Exemplo: Membro,ID1,Misael,123 Street,misael,1234 
Bibliotecario,ID2,Jane,456 Avenue,janesmith,123

**administrador.csv**
Login, senha
Exemplo: admin, 1234

**itens.csv**
- **Livro:** `Livro,ID,Título,Ano,Autor,Editora`
- **Revista:** `Revista,ID,Título,Ano,NúmeroEdição,MêsPublicação`

Exemplo:
Livro,L001,O Senhor dos Anéis,1954,J.R.R. Tolkien,HarperCollins Revista,R001,Ciência Hoje,2023,12,Março


## Como Executar
1. Certifique-se de ter o Java Development Kit (JDK) instalado.
2. Compile o projeto:
    ```bash
    javac BibliotecaGUI.java
    ```
3. Execute o sistema:
    ```bash
    java BibliotecaGUI
    ```
   Certifique-se de que `membros.csv` e `itens.csv` estejam no diretório do projeto.

## Guia de Uso

### Tela de Login
1. **Login**: Insira login e senha, e selecione o tipo de usuário (Membro ou Bibliotecário).
2. **Registro**: Para novos usuários, clique em "Registrar", preencha os dados e salve em `membros.csv`.

### Menu Principal
- **Listar Itens**: Exibe todos os itens da biblioteca.
- **Emprestar Item**: Empréstimo de itens pelo ID.
- **Devolver Item**: Devolução de itens pelo ID.
- **Salvar Dados**: Salva os dados atuais em arquivos CSV.
- **Carregar Dados**: Carrega dados dos arquivos CSV.

Para Bibliotecários:
- **Adicionar Item**: Adiciona um novo item ao acervo.
- **Remover Item**: Remove um item do acervo.

Para Administrador:
- **Editar Membro**: Edita informações do membro.
- **Editar Bibliotecario**: Editar informações do Bibliotecario.

**Observação**: Salve os dados antes de encerrar o programa para garantir a persistência.

## Tecnologias Utilizadas
- **Java SE**: Para a lógica e interface gráfica (Swing).
- **Arquivos CSV**: Para armazenamento persistente dos dados.
- **POO**: Uso de herança, encapsulamento e polimorfismo.

## Melhorias Futuras
- Adicionar sistema de notificações para alertas de empréstimos e prazos de devolução.
- Implementar autenticação avançada e recuperação de conta.
- Expandir o acervo para suportar novos tipos de itens.

