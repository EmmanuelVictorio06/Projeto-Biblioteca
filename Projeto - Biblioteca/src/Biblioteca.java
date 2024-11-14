import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe singleton que representa a biblioteca.
 */
public class Biblioteca {
    // Listas de itens e membros
    private List<Item> listaItens = new ArrayList<>();
    private ArrayList<Membro> listaMembros;
    private List<Bibliotecario> listaBibliotecarios = new ArrayList<>();
    private static Biblioteca instance;
    private static int contadorLivro = 1; // Contador para IDs de livros
    private static int contadorRevista = 1; // Contador para IDs de revistas

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

    // Retorna a lista de membros
    public ArrayList<Membro> getListaMembros() {
        return listaMembros;
    }

    /**
     * Método para adicionar um livro com ID sequencial.
     */
    public void adicionarLivro(String titulo, String autor, int anoPublicacao, String editora) {
        String id = "IDE" + contadorLivro++; // Gera ID único para o livro
        Livro novoLivro = new Livro(id, titulo, anoPublicacao, autor, editora);
        listaItens.add(novoLivro); // Adiciona o livro à lista
        ordenarItens(); // Ordena após adicionar
    }

    /**
     * Método para adicionar uma revista com ID sequencial.
     */
    public void adicionarRevista(String titulo, int anoPublicacao, int numeroEdicao, String mesPublicacao) {
        String id = "IDR" + contadorRevista++; // Gera ID único para a revista
        Revista novaRevista = new Revista(id, titulo, anoPublicacao, numeroEdicao, mesPublicacao);
        listaItens.add(novaRevista); // Adiciona a revista à lista
        ordenarItens(); // Ordena após adicionar
    }

    /**
     * Método para adicionar um item à biblioteca.
     */
    public void adicionarItem(Item item) {
        listaItens.add(item); // Adiciona um item à lista
    }

    /**
     * Método para remover um item da biblioteca.
     */
    public boolean removerItem(String idItem) {
        return listaItens.removeIf(item -> item.getId().equals(idItem)); // Remove item pelo ID
    }

    /**
     * Método para buscar um item pelo ID.
     */
    public Item buscarItem(String id) {
        for (Item item : listaItens) {
            if (item.getId().equalsIgnoreCase(id)) { // Verifica se ID coincide
                return item;
            }
        }
        return null; // Retorna null se item não encontrado
    }

    /**
     * Método para ordenar a lista de itens por título.
     */
    private void ordenarItens() {
        Collections.sort(listaItens, Comparator.comparing(Item::getId)); // Ordena itens por ID
    }

    // Retorna a lista de itens
    public List<Item> getListaItens() {
        return listaItens;
    }

    /**
     * Método para listar todos os itens.
     */
    public void listarItens() {
        ordenarItens(); // Garante que a lista esteja ordenada antes de exibir
        for (Item item : listaItens) {
            item.exibirInformacoes(); // Exibe informações do item
            System.out.println("---------------------------");
        }
    }

    /**
     * Método para salvar dados em arquivos CSV.
     */
    public void salvarDados() {
        salvarItensCSV(); // Salva dados dos itens
        salvarMembrosCSV(); // Salva dados dos membros
    }

    /**
     * Método para carregar dados de arquivos CSV.
     */
    public void carregarDados() {
        carregarItensCSV(); // Carrega dados dos itens
        carregarMembrosCSV(); // Carrega dados dos membros
    }

    // Adiciona um membro à lista de membros
    public void adicionarMembro(Membro membro) {
        listaMembros.add(membro);
    }

    // Métodos privados para manipulação de arquivos
    private void salvarItensCSV() {
        try (PrintWriter pw = new PrintWriter(new File("itens.csv"))) {
            for (Item item : listaItens) {
                if (item instanceof Livro) {
                    Livro livro = (Livro) item;
                    // Salva os dados do livro no formato CSV
                    pw.println("Livro," + livro.getId() + "," + livro.getTitulo() + "," + livro.getAnoPublicacao() + ","
                            + livro.getAutor() + "," + livro.getEditora());
                } else if (item instanceof Revista) {
                    Revista revista = (Revista) item;
                    // Salva os dados da revista no formato CSV
                    pw.println("Revista," + revista.getId() + "," + revista.getTitulo() + "," + revista.getAnoPublicacao()
                            + "," + revista.getNumeroEdicao() + "," + revista.getMesPublicacao());
                }
            }
            System.out.println("Itens salvos com sucesso!");
        } catch (IOException e) {
            // Exibe mensagem de erro caso haja falha ao salvar itens
            System.out.println("Erro ao salvar os itens: " + e.getMessage());
        }
    }

    private void carregarItensCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("itens.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[0].equals("Livro")) {
                    // Cria um novo objeto Livro a partir dos dados lidos
                    Livro livro = new Livro(dados[1], dados[2], Integer.parseInt(dados[3]), dados[4], dados[5]);
                    listaItens.add(livro);
                } else if (dados[0].equals("Revista")) {
                    // Cria um novo objeto Revista a partir dos dados lidos
                    Revista revista = new Revista(dados[1], dados[2], Integer.parseInt(dados[3]),
                            Integer.parseInt(dados[4]), dados[5]);
                    listaItens.add(revista);
                }
            }
            System.out.println("Itens carregados com sucesso!");
            ordenarItens(); // Ordena após carregar
        } catch (IOException e) {
            // Exibe mensagem de erro caso haja falha ao carregar itens
            System.out.println("Erro ao carregar os itens: " + e.getMessage());
        }
    }

    private void salvarMembrosCSV() {
        try (PrintWriter pw = new PrintWriter(new File("membros.csv"))) {
            for (Membro membro : listaMembros) {
                if (membro instanceof Bibliotecario) {
                    // Salva os dados do bibliotecário no formato CSV
                    pw.println("Bibliotecario," + membro.getIdMembro() + "," + membro.getNome() + "," + membro.getEndereco() + "," + membro.getLogin() + "," + membro.getSenha());
                } else {
                    // Salva os dados do membro no formato CSV
                    pw.println("Membro," + membro.getIdMembro() + "," + membro.getNome() + "," + membro.getEndereco() + "," + membro.getLogin() + "," + membro.getSenha());
                }
            }
            System.out.println("Membros salvos com sucesso!");
        } catch (IOException e) {
            // Exibe mensagem de erro caso haja falha ao salvar membros
            System.out.println("Erro ao salvar os membros: " + e.getMessage());
        }
    }

    private void carregarMembrosCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("membros.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println("Reading line: " + linha);  // Debug: Exibe cada linha lida

                String[] dados = linha.split(",");
                if (dados.length < 5) { // Verifica se há pelo menos 5 campos (tipo, ID, nome, login, senha)
                    System.out.println("Error: Line format is incorrect - " + linha);
                    continue;  // Pula linhas com formato incorreto
                }

                String tipoUsuario = dados[0];
                String id = dados[1];
                String nome = dados[2];
                String endereco = dados.length > 5 ? dados[3] : "";  // Campo opcional de endereço
                String login = dados[dados.length > 5 ? 4 : 3];
                String senha = dados[dados.length > 5 ? 5 : 4];

                if (tipoUsuario.equals("Bibliotecario")) {
                    // Cria um novo objeto Bibliotecario a partir dos dados lidos
                    Bibliotecario bibliotecario = new Bibliotecario(id, nome, endereco, login, senha);
                    listaMembros.add(bibliotecario);
                } else if (tipoUsuario.equals("Membro")) {
                    // Cria um novo objeto Membro a partir dos dados lidos
                    Membro membro = new Membro(id, nome, endereco, login, senha);
                    listaMembros.add(membro);
                }
            }
            System.out.println("Membros carregados com sucesso!");
        } catch (IOException e) {
            // Exibe mensagem de erro caso haja falha ao carregar membros
            System.out.println("Erro ao carregar os membros: " + e.getMessage());
        }
    }

    /**
     * Método para autenticar um usuário no sistema pelo ID, nome e tipo de usuário.
     */
    public Membro autenticarUsuario(String login, String senha, String tipoUsuario) {
        for (Membro membro : listaMembros) {
            // Verifica login, senha e tipo de usuário
            if (membro.getLogin().equals(login) && membro.getSenha().equals(senha) && membro.getClass().getSimpleName().equals(tipoUsuario)) {
                return membro;
            }
        }
        return null; // Retorna null se nenhum usuário correspondente for encontrado
    }

    // Busca um bibliotecário pelo ID
    public Bibliotecario buscarBibliotecario(String id) {
        for (Bibliotecario bibliotecario : listaBibliotecarios) {
            if (bibliotecario.getIdMembro().equals(id)) {
                return bibliotecario;
            }
        }
        return null; // Retorna null se o bibliotecário não for encontrado
    }

    // Busca um membro pelo ID
    public Membro buscarMembro(String id) {
        for (Membro membro : listaMembros) {
            if (membro.getIdMembro().equals(id)) {
                return membro;
            }
        }
        return null; // Retorna null se o membro não for encontrado
    }

    // Retorna a lista de bibliotecários
    public List<Bibliotecario> getListaBibliotecarios() {
        return listaBibliotecarios;
    }
}