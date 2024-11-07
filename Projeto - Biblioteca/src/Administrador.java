
// Administrador.java
import java.util.List;

/**
 * Classe que representa um administrador, herdando de Membro.
 * O administrador possui permissões para gerenciar membros e bibliotecários.
 */
public class Administrador extends Membro {

    public Administrador(String idMembro, String nome, String endereco, String login, String senha) {
        super(idMembro, nome, endereco, login, senha);
    }

    // Método para editar um membro
    public void editarMembro(Membro membro, String novoNome, String novoEndereco) {
        membro.setNome(novoNome);
        membro.setEndereco(novoEndereco);
        System.out.println("Membro atualizado com sucesso!");
    }

    // Método para excluir um membro
    public void excluirMembro(List<Membro> membros, Membro membro) {
        membros.remove(membro);
        System.out.println("Membro excluído com sucesso!");
    }

    // Método para editar um bibliotecário
    public void editarBibliotecario(Bibliotecario bibliotecario, String novoNome, String novoEndereco) {
        bibliotecario.setNome(novoNome);
        bibliotecario.setEndereco(novoEndereco);
        System.out.println("Bibliotecário atualizado com sucesso!");
    }

    // Método para excluir um bibliotecário
    public void excluirBibliotecario(List<Bibliotecario> bibliotecarios, Bibliotecario bibliotecario) {
        bibliotecarios.remove(bibliotecario);
        System.out.println("Bibliotecário excluído com sucesso!");
    }
}
