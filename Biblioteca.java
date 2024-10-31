import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("Livro adicionado: " + livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        if (usuario instanceof Cliente) {
            System.out.println("=== Novo Cliente Adicionado ===");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("==============================");
        } else if (usuario instanceof Funcionario) {
            System.out.println("=== Novo Funcionário Adicionado ===");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("==============================");
        }
    }

    public void emprestarLivro(String titulo, Cliente cliente) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo) && !livro.isEmprestado()) {
                livro.emprestar();
                System.out.println(cliente.getNome() + " emprestou " + livro);
                return;
            }
        }
        System.out.println("=== Empréstimo Não Realizado ===");
        System.out.println("Livro não disponível para empréstimo.");
        System.out.println("==============================");
    }

    public void devolverLivro(String titulo, Cliente cliente) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo) && livro.isEmprestado()) {
                livro.devolver();
                System.out.println(cliente.getNome() + " devolveu " + livro);
                return;
            }
        }
        System.out.println("=== Devolução Não Realizada ===");
        System.out.println("Livro não encontrado ou não foi emprestado.");
        System.out.println("==============================");
    }

    public void mostrarLivrosDisponiveis() {
        System.out.println("\n=== Livros Disponíveis ===");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
        System.out.println("==============================\n");
    }
}
