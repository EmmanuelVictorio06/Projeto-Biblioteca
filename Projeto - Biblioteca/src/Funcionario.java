public class Funcionario extends Usuario {

    public Funcionario(String nome, String id) {
        super(nome, id);
    }

    @Override
    public void mostrarInformacoes() {
        System.out.println("Funcionário: " + getNome() + ", ID: " + getId());
    }
}