// Membro.java
import java.util.ArrayList;

/**
 * Classe que representa um membro da biblioteca.
 */
public class Membro {
    protected String idMembro;
    protected String nome;
    protected String endereco;
    protected String login;
    protected String senha;
    protected ArrayList<Item> itensEmprestados;

    public Membro(String idMembro, String nome, String endereco, String login, String senha) {
        this.idMembro = idMembro;
        this.nome = nome;
        this.endereco = endereco;
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

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

    public void setLogin(String login) {
        this.login = login;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
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
