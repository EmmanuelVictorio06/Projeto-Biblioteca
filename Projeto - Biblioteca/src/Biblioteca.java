// Biblioteca.java
import java.io.*;
import java.util.ArrayList;

/**
 * Classe singleton que representa a biblioteca.
 */
public class Biblioteca {
    // Listas de itens e membros
    public ArrayList<Item> listaItens;
    public ArrayList<Membro> listaMembros;

    private static Biblioteca instance;

    /**
     * Construtor privado para implementação do padrão Singleton.
     */
    private Biblioteca() {
        listaItens = new ArrayList<>();
        listaMembros = new ArrayList<>();
    }

    /**
     * Método para obter a instância única da biblioteca.
     */
    public static Biblioteca getInstance() {
        if (instance == null) {
            instance = new Biblioteca();
        }
        return instance;
    }

    /**
     * Método para adicionar um item à biblioteca.
     */
    public void adicionarItem(Item item) {
        listaItens.add(item);
    }

    /**
     * Método para remover um item da biblioteca.
     */
    public boolean removerItem(String idItem) {
        for (Item item : listaItens) {
            if (item.getId().equals(idItem)) {
                listaItens.remove(item);
                return true;
            }
        }
        return false;
    }

    /**
     * Método para buscar um item pelo ID.
     */
    public Item buscarItem(String id) {
        for (Item item : listaItens) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Método para listar todos os itens.
     */
    public void listarItens() {
        for (Item item : listaItens) {
            item.exibirInformacoes();
            System.out.println("---------------------------");
        }
    }

    /**
     * Método para salvar dados em arquivos CSV.
     */
    public void salvarDados() {
        salvarItensCSV();
        salvarMembrosCSV();
    }

    /**
     * Método para carregar dados de arquivos CSV.
     */
    public void carregarDados() {
        carregarItensCSV();
        carregarMembrosCSV();
    }

    // Métodos privados para manipulação de arquivos
    private void salvarItensCSV() {
        try (PrintWriter pw = new PrintWriter(new File("itens.csv"))) {
            for (Item item : listaItens) {
                if (item instanceof Livro) {
                    Livro livro = (Livro) item;
                    pw.println("Livro," + livro.getId() + "," + livro.getTitulo() + "," + livro.getAnoPublicacao() + ","
                            + livro.getAutor() + "," + livro.getEditora());
                } else if (item instanceof Revista) {
                    Revista revista = (Revista) item;
                    pw.println("Revista," + revista.getId() + "," + revista.getTitulo() + "," + revista.getAnoPublicacao()
                            + "," + revista.getNumeroEdicao() + "," + revista.getMesPublicacao());
                }
            }
            System.out.println("Itens salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os itens: " + e.getMessage());
        }
    }

    private void carregarItensCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("itens.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[0].equals("Livro")) {
                    Livro livro = new Livro(dados[1], dados[2], Integer.parseInt(dados[3]), dados[4], dados[5]);
                    listaItens.add(livro);
                } else if (dados[0].equals("Revista")) {
                    Revista revista = new Revista(dados[1], dados[2], Integer.parseInt(dados[3]),
                            Integer.parseInt(dados[4]), dados[5]);
                    listaItens.add(revista);
                }
            }
            System.out.println("Itens carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os itens: " + e.getMessage());
        }
    }

    private void salvarMembrosCSV() {
        try (PrintWriter pw = new PrintWriter(new File("membros.csv"))) {
            for (Membro membro : listaMembros) {
                if (membro instanceof Bibliotecario) {
                    pw.println("Bibliotecario," + membro.getIdMembro() + "," + membro.getNome() + "," + membro.getEndereco());
                } else {
                    pw.println("Membro," + membro.getIdMembro() + "," + membro.getNome() + "," + membro.getEndereco());
                }
            }
            System.out.println("Membros salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os membros: " + e.getMessage());
        }
    }

    private void carregarMembrosCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\emmanuel.victorio\\Downloads\\Projeto-Biblioteca\\Projeto - Biblioteca\\src\\membros.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[0].equals("Bibliotecario")) {
                    Bibliotecario bibliotecario = new Bibliotecario(dados[1], dados[2], dados[3]);
                    listaMembros.add(bibliotecario);
                } else if (dados[0].equals("Membro")) {
                    Membro membro = new Membro(dados[1], dados[2], dados[3]);
                    listaMembros.add(membro);
                }
            }
            System.out.println("Membros carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os membros: " + e.getMessage());
        }
    }

    /**
     * Método para autenticar um usuário no sistema.
     */
    public Membro autenticarUsuario(String id, String nome) {
        for (Membro membro : listaMembros) {
            if (membro.getIdMembro().equals(id) && membro.getNome().equals(nome)) {
                return membro;
            }
        }
        return null;
    }
}
