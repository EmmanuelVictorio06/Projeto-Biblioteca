public class Cliente extends Usuario {

    public Cliente(String nome, String id) {
        super(nome, id);
    }

    @Override
    public void mostrarInformacoes() {
        System.out.println("Cliente: " + getNome() + ", ID: " + getId());
    }
}