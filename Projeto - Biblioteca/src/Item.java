// Item.java

/**
 * Classe abstrata que representa um item geral da biblioteca.
 */
public abstract class Item {
    private String id;
    private String titulo;
    private int anoPublicacao;

    /**
     * Construtor da classe Item.
     */
    public Item(String id, String titulo, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
    }

    // Métodos getters e setters
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    /**
     * Método abstrato para exibir informações do item.
     */
    public abstract void exibirInformacoes();
}
