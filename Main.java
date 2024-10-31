public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Adicionando livros à biblioteca
        biblioteca.adicionarLivro(new Livro("1984", "George Orwell"));
        biblioteca.adicionarLivro(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien"));

        // Criando usuários
        Cliente cliente1 = new Cliente("Alice", "C001");
        Funcionario funcionario1 = new Funcionario("Carlos", "F001");

        // Adicionando usuários à biblioteca
        biblioteca.adicionarUsuario(cliente1);
        biblioteca.adicionarUsuario(funcionario1);

        // Mostrando livros disponíveis
        biblioteca.mostrarLivrosDisponiveis();

        // Emprestando um livro
        biblioteca.emprestarLivro("1984", cliente1);
        biblioteca.mostrarLivrosDisponiveis();

        // Devolvendo um livro
        biblioteca.devolverLivro("1984", cliente1);
        biblioteca.mostrarLivrosDisponiveis();

        funcionario1.mostrarInformacoes();
    }
}
