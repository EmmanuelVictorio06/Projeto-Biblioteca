// Revista.java

/**
 * Classe que representa uma revista, herda de Item.
 */
public class Revista extends Item {
    private int numeroEdicao;
    private String mesPublicacao;

    /**
     * Construtor da classe Revista.
     */
    public Revista(String id, String titulo, int anoPublicacao, int numeroEdicao, String mesPublicacao) {
        super(id, titulo, anoPublicacao);
        this.numeroEdicao = numeroEdicao;
        this.mesPublicacao = mesPublicacao;
    }

    // Métodos getters e setters
    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public String getMesPublicacao() {
        return mesPublicacao;
    }

    public void setNumeroEdicao(int numeroEdicao) {
        this.numeroEdicao = numeroEdicao;
    }

    public void setMesPublicacao(String mesPublicacao) {
        this.mesPublicacao = mesPublicacao;
    }

    /**
     * Implementação do método abstrato para exibir informações da revista.
     */
    @Override
    public void exibirInformacoes() {
        System.out.println("Revista: " + getTitulo() + " (" + getAnoPublicacao() + ")");
        System.out.println("Edição nº: " + numeroEdicao);
        System.out.println("Mês de Publicação: " + mesPublicacao);
    }
}
