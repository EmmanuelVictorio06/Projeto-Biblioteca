// Membro.java
import java.util.ArrayList;

/**
 * Classe que representa um membro da biblioteca.
 */
public class Membro {
    protected String idMembro;
    protected String nome;
    protected String endereco;
    protected ArrayList<Item> itensEmprestados;

    /**
     * Construtor da classe Membro.
     */
    public Membro(String idMembro, String nome, String endereco) {
        this.idMembro = idMembro;
        this.nome = nome;
        this.endereco = endereco;
        this.itensEmprestados = new ArrayList<>();
    }

    // Métodos getters e setters
    public String getIdMembro() {
        return idMembro;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setIdMembro(String idMembro) {
        this.idMembro = idMembro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Método para emprestar um item.
     */
    public void emprestarItem(Item item) throws ItemIndisponivelException {
        if (item == null) {
            throw new IllegalArgumentException("Item inválido.");
        }
        if (Biblioteca.getInstance().removerItem(item.getId())) {
            itensEmprestados.add(item);
            System.out.println("Item emprestado com sucesso!");
        } else {
            throw new ItemIndisponivelException("O item não está disponível para empréstimo.");
        }
    }

    /**
     * Método para devolver um item.
     */
    public void devolverItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item inválido.");
        }
        if (itensEmprestados.remove(item)) {
            Biblioteca.getInstance().adicionarItem(item);
            System.out.println("Item devolvido com sucesso!");
        } else {
            System.out.println("Este item não pertence a este membro.");
        }
    }
}
