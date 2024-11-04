// Bibliotecario.java

/**
 * Classe que representa um bibliotecário, herda de Membro.
 */
public class Bibliotecario extends Membro {

    /**
     * Construtor da classe Bibliotecario.
     */
    public Bibliotecario(String idMembro, String nome, String endereco) {
        super(idMembro, nome, endereco);
    }

    /**
     * Método para adicionar um item à biblioteca.
     */
    public void adicionarItem(Item item) {
        Biblioteca.getInstance().adicionarItem(item);
        System.out.println("Item adicionado com sucesso!");
    }

    /**
     * Método para remover um item da biblioteca.
     */
    public void removerItem(String idItem) {
        if (Biblioteca.getInstance().removerItem(idItem)) {
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Item não encontrado.");
        }
    }
}
