

// Livro.java

/**
 * Classe que representa um livro, herda de Item.
 */
public class Livro extends Item {
    private String autor;
    private String editora;

    /**
     * Construtor da classe Livro.
     */
    public Livro(String id, String titulo, int anoPublicacao, String autor, String editora) {
        super(id, titulo, anoPublicacao);
        this.autor = autor;
        this.editora = editora;
    }

    // Métodos getters e setters
    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Implementação do método abstrato para exibir informações do livro.
     */
    @Override
    public void exibirInformacoes() {
        System.out.println("Livro: " + getTitulo() + " (" + getAnoPublicacao() + ")");
        System.out.println("Autor: " + autor);
        System.out.println("Editora: " + editora);
    }
}
