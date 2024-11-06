import java.io.*;
import java.util.ArrayList;

/**
 * Classe singleton que representa a biblioteca.
 */
public class Biblioteca {
    // Listas de itens e membros
    private ArrayList<Item> listaItens;
    private ArrayList<Membro> listaMembros;

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
    
    public ArrayList<Membro> getListaMembros() {
        return listaMembros;
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
        return listaItens.removeIf(item -> item.getId().equals(idItem));
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

    public void adicionarMembro(Membro membro) {
        listaMembros.add(membro);
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
        try (BufferedReader br = new BufferedReader(new FileReader("membros.csv"))) {
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
        try (BufferedReader br = new BufferedReader(new FileReader("membros.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println("Reading line: " + linha);  // Debug: Print each line being read
    
                String[] dados = linha.split(",");
                if (dados.length < 5) { // Ensure there are at least 5 fields
                    System.out.println("Error: Line format is incorrect - " + linha);
                    continue;  // Skip improperly formatted lines
                }
    
                if (dados[0].equals("Bibliotecario")) {
                    // Instantiate Bibliotecario with five fields, defaulting endereco to an empty string
                    Bibliotecario bibliotecario = new Bibliotecario(dados[1], dados[2], "", dados[3], dados[4]);
                    listaMembros.add(bibliotecario);
                } else if (dados[0].equals("Membro")) {
                    // Instantiate Membro with five fields, defaulting endereco to an empty string
                    Membro membro = new Membro(dados[1], dados[2], "", dados[3], dados[4]);
                    listaMembros.add(membro);
                }
            }
            System.out.println("Membros carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os membros: " + e.getMessage());
        }
    }
        
    /**
     * Método para autenticar um usuário no sistema pelo ID, nome e tipo de usuário.
     */
    public Membro autenticarUsuario(String login, String senha, String tipoUsuario) {
        for (Membro membro : listaMembros) {
            // Verify login, senha, and user type
            if (membro.getLogin().equals(login) && membro.getSenha().equals(senha) && membro.getClass().getSimpleName().equals(tipoUsuario)) {
                return membro;
            }
        }
        return null; // Return null if no matching user is found
    }
    

    public Item[] getListaItens() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListaItens'");
    }
}
