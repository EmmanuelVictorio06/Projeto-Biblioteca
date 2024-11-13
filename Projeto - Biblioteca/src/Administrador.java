import java.util.List;

/**
 * Classe que representa um administrador, herdando de Membro.
 * O administrador possui permissões para gerenciar membros e bibliotecários.
 */
public class Administrador extends Membro {

    // Construtor da classe Administrador
    public Administrador(String idMembro, String nome, String tipo, String login, String senha) {
        super(idMembro, nome, tipo, login, senha);
    }

    // Método para editar um membro
    public void editarMembro(Membro membro, String novoNome, String novoTipo) {
        // Atualiza o nome e endereço do membro
        membro.setNome(novoNome);
        membro.setTipoUsuario(novoTipo);
        System.out.println("Membro atualizado com sucesso!");
    }

    // Método para excluir um membro
    public void excluirMembro(List<Membro> membros, Membro membro) {
        // Remove o membro da lista de membros
        membros.remove(membro);
        System.out.println("Membro excluído com sucesso!");
    }

    // Método para editar um bibliotecário
    public void editarBibliotecario(Bibliotecario bibliotecario, String novoNome, String novoTipo) {
        // Atualiza o nome e endereço do bibliotecário
        bibliotecario.setNome(novoNome);
        bibliotecario.setTipoUsuario(novoTipo);
        System.out.println("Bibliotecário atualizado com sucesso!");
    }

    // Método para excluir um bibliotecário
    public void excluirBibliotecario(List<Bibliotecario> bibliotecarios, Bibliotecario bibliotecario) {
        // Remove o bibliotecário da lista de bibliotecários
        bibliotecarios.remove(bibliotecario);
        System.out.println("Bibliotecário excluído com sucesso!");
    }
}