# Sistema de Gerenciamento de Biblioteca
Descrição
Este é um projeto de sistema de gerenciamento de biblioteca desenvolvido em Java. O sistema permite gerenciar itens como livros e revistas, além de realizar operações de empréstimo e devolução. Existem dois tipos de usuários no sistema: Membro e Bibliotecario, sendo que apenas o bibliotecário possui permissões para adicionar ou remover itens do acervo.
O sistema utiliza princípios de Programação Orientada a Objetos (POO) como encapsulamento, herança e polimorfismo, e permite a persistência de dados por meio de arquivos CSV (membros.csv e itens.csv).
Funcionalidades
•	Cadastro de usuários (Bibliotecário ou Membro) e login com autenticação.
•	Gerenciamento de itens: adição, remoção, listagem, empréstimo e devolução de itens.
•	Persistência de dados: os dados dos membros e itens são armazenados em arquivos CSV, permitindo que sejam carregados quando o sistema é iniciado.
Estrutura do Projeto
•	BibliotecaGUI.java: Interface gráfica para o sistema, responsável por exibir a tela de login, registro, e o menu principal com as operações disponíveis.
•	Biblioteca.java: Classe singleton que gerencia o acervo de itens e a lista de membros.
•	Membro.java: Classe base para todos os usuários do sistema, com atributos como idMembro, nome, login, senha e endereco.
•	Bibliotecario.java: Classe que herda de Membro e representa um usuário com permissões especiais para gerenciar itens.
•	Item.java: Classe abstrata que representa um item da biblioteca, como livro ou revista.
•	Livro.java e Revista.java: Classes que herdam de Item e representam itens específicos do acervo.
•	ItemIndisponivelException.java: Exceção personalizada que indica quando um item não está disponível para empréstimo.
•	Arquivos CSV:
o	membros.csv: Armazena os dados dos usuários (tipo, ID, nome, endereço, login, senha).
o	itens.csv: Armazena os dados dos itens no acervo (tipo, ID, título, ano de publicação, e outros atributos específicos para livros e revistas).
Estrutura dos Arquivos CSV
membros.csv
Cada linha deve ter o seguinte formato:
Copiar código
Tipo, ID, Nome, Endereco, Login, Senha
Exemplo:
Copiar código
Membro,ID1,Misael,123 Street,misael,1234
Bibliotecario,ID2,Jane,456 Avenue,janesmith,password
itens.csv
Cada linha deve ter o seguinte formato, dependendo do tipo de item:
•	Livro:
r
Copiar código
Livro,ID,Título,Ano,Autor,Editora
•	Revista:
mathematica
Copiar código
Revista,ID,Título,Ano,NúmeroEdição,MêsPublicação
Exemplo:
csharp
Copiar código
Livro,L001,O Senhor dos Anéis,1954,J.R.R. Tolkien,HarperCollins
Revista,R001,Ciência Hoje,2023,12,Março
Como Executar
1.	Certifique-se de ter o Java Development Kit (JDK) instalado em seu sistema.
2.	Compile o projeto utilizando o comando:
bash
Copiar código
javac BibliotecaGUI.java
3.	Execute o sistema:
bash
Copiar código
java BibliotecaGUI
Certifique-se de que os arquivos membros.csv e itens.csv estejam no mesmo diretório do projeto para garantir que os dados sejam carregados corretamente.
Guia de Uso
Tela de Login
1.	Login: Insira seu login e senha, e selecione seu tipo de usuário (Membro ou Bibliotecario). Caso as credenciais estejam corretas, o sistema abrirá o menu principal.
2.	Registro: Caso seja um novo usuário, clique em "Registrar" para se cadastrar. Insira seu nome, login, senha e selecione o tipo de usuário. Os dados serão salvos em membros.csv.
Menu Principal
Após o login, o menu exibe diferentes opções, dependendo do tipo de usuário:
•	Listar Itens: Exibe todos os itens da biblioteca.
•	Emprestar Item: Permite emprestar um item pelo ID.
•	Devolver Item: Permite devolver um item emprestado pelo ID.
•	Salvar Dados: Salva os dados atuais dos itens e membros nos arquivos CSV.
•	Carregar Dados: Carrega os dados dos arquivos CSV.
Para Bibliotecários, o menu possui opções adicionais:
•	Adicionar Item: Permite adicionar um novo item à biblioteca.
•	Remover Item: Permite remover um item da biblioteca.
Observação
Para garantir que os dados sejam persistidos após o encerramento do programa, clique em Salvar Dados antes de sair.
Tecnologias Utilizadas
•	Java SE: Para a lógica do sistema e interface gráfica com Swing.
•	Arquivos CSV: Para armazenamento persistente dos dados.
•	Programação Orientada a Objetos: Uso de conceitos de herança, encapsulamento e polimorfismo.
Melhorias Futuras
•	Adicionar um sistema de notificações para alertar os usuários sobre itens emprestados e prazos de devolução.
•	Implementar autenticação avançada com validação de senha e recuperação de conta.
•	Expandir o acervo para suportar novos tipos de itens.
