import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe que representa a interface gráfica da biblioteca.
 */
public class BibliotecaGUI extends JFrame {
    private JTextArea textArea;
    private Membro usuarioAtual;

    public BibliotecaGUI() {
        setTitle("Sistema de Gerenciamento de Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Inicializa e adiciona a textArea ao layout
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 150, 740, 400);
        add(scrollPane);

        // Inicia a tela de login
        telaLogin();
    }
    
    // Método para exibir a tela de edição de membro
    private void editarMembro() {
        exibirLista("Membro", true);
    }

    // Método para exibir a tela de edição de bibliotecário
    private void editarBibliotecario() {
        exibirLista("Bibliotecario", true);
    }

    // Exibe a lista de usuários (membros ou bibliotecários) para editar ou excluir
    private void exibirLista(String tipoUsuario, boolean isEditar) {
        JDialog dialog = new JDialog(this, "Lista de " + tipoUsuario + "s", true);
        dialog.setSize(600, 400);
        dialog.setLayout(new BorderLayout());

        // Obtem a lista de usuários do tipo especificado
        List<?> usuarios = tipoUsuario.equals("Membro") ? Biblioteca.getInstance().getListaMembros()
                                                        : Biblioteca.getInstance().getListaBibliotecarios();
        String[] colunas = {"ID", "Nome", "Endereço", "Login"};
        String[][] dados = new String[usuarios.size()][4];

        // Preenche a tabela com os dados dos usuários
        for (int i = 0; i < usuarios.size(); i++) {
            if (tipoUsuario.equals("Membro")) {
                Membro membro = (Membro) usuarios.get(i);
                dados[i][0] = membro.getIdMembro();
                dados[i][1] = membro.getNome();
                dados[i][2] = membro.getEndereco();
                dados[i][3] = membro.getLogin();
            } else {
                Bibliotecario bibliotecario = (Bibliotecario) usuarios.get(i);
                dados[i][0] = bibliotecario.getIdMembro();
                dados[i][1] = bibliotecario.getNome();
                dados[i][2] = bibliotecario.getEndereco();
                dados[i][3] = bibliotecario.getLogin();
            }
        }

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabela);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel();

        // Botão para excluir o usuário selecionado
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada != -1) {
                String id = (String) tabela.getValueAt(linhaSelecionada, 0);

                if (tipoUsuario.equals("Membro")) {
                    Membro membro = Biblioteca.getInstance().buscarMembro(id);
                    if (membro != null) {
                        Biblioteca.getInstance().getListaMembros().remove(membro);
                        JOptionPane.showMessageDialog(dialog, "Membro excluído com sucesso!");
                    }
                } else {
                    Bibliotecario bibliotecario = Biblioteca.getInstance().buscarBibliotecario(id);
                    if (bibliotecario != null) {
                        Biblioteca.getInstance().getListaBibliotecarios().remove(bibliotecario);
                        JOptionPane.showMessageDialog(dialog, "Bibliotecário excluído com sucesso!");
                    }
                }
                dialog.dispose();
                exibirLista(tipoUsuario, isEditar); // Atualiza a lista
            } else {
                JOptionPane.showMessageDialog(dialog, "Selecione um " + tipoUsuario + " para excluir.");
            }
        });

        panelBotoes.add(btnExcluir);

        // Adiciona o botão de editar se isEditar for true
        if (isEditar) {
            JButton btnEditar = new JButton("Editar");
            btnEditar.addActionListener(e -> {
                int linhaSelecionada = tabela.getSelectedRow();
                if (linhaSelecionada != -1) {
                    String id = (String) tabela.getValueAt(linhaSelecionada, 0);
                    abrirJanelaEdicao(id, tipoUsuario);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Selecione um " + tipoUsuario + " para editar.");
                }
            });
            panelBotoes.add(btnEditar);
        }

        dialog.add(panelBotoes, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Abre a janela de edição para o usuário selecionado
    private void abrirJanelaEdicao(String id, String tipoUsuario) {
        JDialog editDialog = new JDialog(this, "Editar " + tipoUsuario, true);
        editDialog.setSize(400, 350);
        editDialog.setLayout(new GridLayout(6, 2));

        // Obtendo o usuário selecionado
        Membro usuario = tipoUsuario.equals("Membro") ? Biblioteca.getInstance().buscarMembro(id)
                                                      : Biblioteca.getInstance().buscarBibliotecario(id);

        if (usuario == null) return; // Caso o usuário não seja encontrado

        // Campos para edição
        JTextField txtNome = new JTextField(usuario.getNome());
        JTextField txtEndereco = new JTextField(usuario.getEndereco());
        JTextField txtLogin = new JTextField(usuario.getLogin());
        JPasswordField txtSenha = new JPasswordField(usuario.getSenha());

        editDialog.add(new JLabel("ID:"));
        editDialog.add(new JLabel(usuario.getIdMembro())); // Exibe o ID como label, não editável
        editDialog.add(new JLabel("Nome:"));
        editDialog.add(txtNome);
        editDialog.add(new JLabel("Endereço:"));
        editDialog.add(txtEndereco);
        editDialog.add(new JLabel("Login:"));
        editDialog.add(txtLogin);
        editDialog.add(new JLabel("Senha:"));
        editDialog.add(txtSenha);

        // Botão para salvar as alterações
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            boolean alterado = false;

            // Verifica e atualiza os campos alterados
            if (!txtNome.getText().equals(usuario.getNome())) {
                usuario.setNome(txtNome.getText());
                alterado = true;
            }
            if (!txtEndereco.getText().equals(usuario.getEndereco())) {
                usuario.setEndereco(txtEndereco.getText());
                alterado = true;
            }
            if (!txtLogin.getText().equals(usuario.getLogin())) {
                usuario.setLogin(txtLogin.getText());
                alterado = true;
            }
            if (!new String(txtSenha.getPassword()).equals(usuario.getSenha())) {
                usuario.setSenha(new String(txtSenha.getPassword()));
                alterado = true;
            }

            if (alterado) {
                JOptionPane.showMessageDialog(editDialog, tipoUsuario + " editado com sucesso!");
                editDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(editDialog, "Nenhuma alteração realizada.");
            }
        });

        // Botão para cancelar a edição
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> editDialog.dispose());

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);

        editDialog.add(panelBotoes);

        editDialog.setLocationRelativeTo(this);
        editDialog.setVisible(true);
    }

    /**
     * Método para exibir a tela de login.
     */
    private void telaLogin() {
        JTextField txtLogin = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        JComboBox<String> userTypeDropdown = new JComboBox<>(new String[]{"Membro", "Bibliotecario", "Administrador"});

        Object[] message = {
            "Login:", txtLogin,
            "Senha:", txtSenha,
            "Tipo de Usuário:", userTypeDropdown
        };

        Object[] options = {"OK", "Cancelar", "Registrar"};
        int option = JOptionPane.showOptionDialog(this, message, "Login", JOptionPane.YES_NO_CANCEL_OPTION, 
                                                  JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (option == JOptionPane.YES_OPTION) { // OK button clicked
            String login = txtLogin.getText();
            String senha = new String(txtSenha.getPassword());
            String tipoUsuario = (String) userTypeDropdown.getSelectedItem();

            if ("Administrador".equals(tipoUsuario)) {
                usuarioAtual = autenticarAdministrador(login, senha);
            } else {
                usuarioAtual = Biblioteca.getInstance().autenticarUsuario(login, senha, tipoUsuario);
            }

            if (usuarioAtual != null) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuarioAtual.getNome() + "!");
                menuPrincipal();
            } else {
                JOptionPane.showMessageDialog(this, "Login, senha ou tipo de usuário incorretos.");
                telaLogin();
            }
        } else if (option == JOptionPane.CANCEL_OPTION) {
            // Somente membros e bibliotecários podem se registrar
            if (!"Administrador".equals(userTypeDropdown.getSelectedItem())) {
                telaRegistro();
            } else {
                JOptionPane.showMessageDialog(this, "Administradores não podem se registrar.");
            }
        } else {
            System.exit(0);
        }
    }

    // Método para autenticar o administrador a partir de um arquivo CSV
    private Administrador autenticarAdministrador(String login, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader("admin.csv"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[0].equals(login) && dados[1].equals(senha)) {
                    return new Administrador("adminID", "Administrador", "Endereco Admin", login, senha);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo admin.csv: " + e.getMessage());
        }
        return null; // Retorna null se a autenticação falhar
    }

    // Tela de registro para novos membros e bibliotecários
    private void telaRegistro() {
        JTextField txtNome = new JTextField();
        JTextField txtLogin = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        JComboBox<String> userTypeDropdown = new JComboBox<>(new String[]{"Membro", "Bibliotecario"});

        Object[] message = {
            "Nome:", txtNome,
            "Login:", txtLogin,
            "Senha:", txtSenha,
            "Tipo de Usuário:", userTypeDropdown
        };

    int option = JOptionPane.showConfirmDialog(this, message, "Registro", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        String nome = txtNome.getText();
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());
        String tipoUsuario = (String) userTypeDropdown.getSelectedItem();

        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
            telaRegistro();
        } else {
            // Generate a unique ID for the new user
            String id = generateUniqueId();

            // Save the new user to membros.csv
            try (FileWriter writer = new FileWriter("membros.csv", true)) {
                writer.write(tipoUsuario + "," + id + "," + nome + "," + login + "," + senha + "\n");
                JOptionPane.showMessageDialog(this, tipoUsuario + " registrado com sucesso!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar os dados: " + e.getMessage());
            }

            // Return to the login screen after registration
            telaLogin();
        }
    }
}

    // Método para gerar IDs únicos (pode ser uma implementação simples)
    private String generateUniqueId() {
        return "ID" + (Biblioteca.getInstance().getListaMembros().size() + 1);
    }

    /**
     * Método para exibir o menu principal após o login.
     */
    private void menuPrincipal() {
        JButton btnListarItens = new JButton("Listar Itens");
        JButton btnEmprestarItem = new JButton("Emprestar Item");
        JButton btnDevolverItem = new JButton("Devolver Item");
        JButton btnSalvarDados = new JButton("Salvar Dados");
        JButton btnCarregarDados = new JButton("Carregar Dados");

        // Definição de posições dos botões
        btnListarItens.setBounds(20, 20, 150, 30);
        btnEmprestarItem.setBounds(180, 20, 150, 30);
        btnDevolverItem.setBounds(340, 20, 150, 30);
        btnSalvarDados.setBounds(500, 20, 150, 30);
        btnCarregarDados.setBounds(660, 20, 150, 30);

        // Adiciona os botões à interface
        add(btnListarItens);
        add(btnEmprestarItem);
        add(btnDevolverItem);
        add(btnSalvarDados);
        add(btnCarregarDados);

        btnListarItens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarItens(); // Permite que todos visualizem a lista de itens
            }
        });

        btnEmprestarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emprestarItem();
            }
        });

        btnDevolverItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverItem();
            }
        });

        btnSalvarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Biblioteca.getInstance().salvarDados();
            }
        });

        btnCarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Biblioteca.getInstance().carregarDados();
            }
        });

        // Funções exclusivas para bibliotecários
        if (usuarioAtual instanceof Bibliotecario) {
            JButton btnAdicionarItem = new JButton("Adicionar Item");
            JButton btnRemoverItem = new JButton("Remover Item");

            btnAdicionarItem.setBounds(20, 70, 150, 30);
            btnRemoverItem.setBounds(180, 70, 150, 30);

            add(btnAdicionarItem);
            add(btnRemoverItem);

            btnAdicionarItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adicionarItem();
                }
            });

            btnRemoverItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removerItem();
                }
            });
        }

        // Funções exclusivas para administradores
        if (usuarioAtual instanceof Administrador) {
            JButton btnEditarMembro = new JButton("Editar Membro");
            JButton btnEditarBibliotecario = new JButton("Editar Bibliotecário");

            btnEditarMembro.setBounds(20, 120, 150, 30);
            btnEditarBibliotecario.setBounds(180, 120, 150, 30);

            add(btnEditarMembro);
            add(btnEditarBibliotecario);

            btnEditarMembro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editarMembro();
                }
            });

            btnEditarBibliotecario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editarBibliotecario();
                }
            });
        }
    }
    /**
     * Método para listar os itens na área de texto.
     */
    private void listarItens() {
        textArea.setText(""); // Limpa a área de texto para exibir a lista atualizada
        for (Item item : Biblioteca.getInstance().getListaItens()) {
            textArea.append(item.getTitulo() + " - " + item.getId() + "\n");
        }
    }

    private void adicionarItem() {
        String[] opcoes = {"Livro", "Revista"};
        String tipo = (String) JOptionPane.showInputDialog(this, "Selecione o tipo de item:", "Adicionar Item",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (tipo != null) {
            if (tipo.equals("Livro")) {
                adicionarLivro();
            } else if (tipo.equals("Revista")) {
                adicionarRevista();
            }
            listarItens(); // Atualiza a lista imediatamente após adicionar um item
        }
    }

    /**
     * Método para adicionar um livro.
     */
    private void adicionarLivro() {
        try {
            String id = JOptionPane.showInputDialog("ID do Livro:");
            String titulo = JOptionPane.showInputDialog("Título do Livro:");
            int ano = Integer.parseInt(JOptionPane.showInputDialog("Ano de Publicação:"));
            String autor = JOptionPane.showInputDialog("Autor:");
            String editora = JOptionPane.showInputDialog("Editora:");

            if (id.isEmpty() || titulo.isEmpty() || autor.isEmpty() || editora.isEmpty()) {
                throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
            }

            Livro livro = new Livro(id, titulo, ano, autor, editora);
            Biblioteca.getInstance().adicionarItem(livro); // Adiciona o livro à lista de itens da biblioteca
            JOptionPane.showMessageDialog(this, "Livro adicionado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ano de publicação inválido.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void adicionarRevista() {
        try {
            String id = JOptionPane.showInputDialog("ID da Revista:");
            String titulo = JOptionPane.showInputDialog("Título da Revista:");
            int ano = Integer.parseInt(JOptionPane.showInputDialog("Ano de Publicação:"));
            int numeroEdicao = Integer.parseInt(JOptionPane.showInputDialog("Número da Edição:"));
            String mesPublicacao = JOptionPane.showInputDialog("Mês de Publicação:");

            if (id.isEmpty() || titulo.isEmpty() || mesPublicacao.isEmpty()) {
                throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
            }

            Revista revista = new Revista(id, titulo, ano, numeroEdicao, mesPublicacao);
            Biblioteca.getInstance().adicionarItem(revista); // Adiciona a revista à lista de itens da biblioteca
            JOptionPane.showMessageDialog(this, "Revista adicionado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dados numéricos inválidos.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Método para remover um item (apenas para bibliotecários).
     */
    private void removerItem() {
        String idItem = JOptionPane.showInputDialog("Informe o ID do item a ser removido:");
        if (Biblioteca.getInstance().removerItem(idItem)) {
            JOptionPane.showMessageDialog(this, "Item removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Item não encontrado.");
        }
    }

    /**
     * Método para emprestar um item.
     */
    private void emprestarItem() {
        String idItem = JOptionPane.showInputDialog("Informe o ID do item para empréstimo:");
        Item item = Biblioteca.getInstance().buscarItem(idItem);
        if (item != null) {
            try {
                usuarioAtual.emprestarItem(item);
                JOptionPane.showMessageDialog(this, "Item emprestado com sucesso!");
            } catch (ItemIndisponivelException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Item não encontrado.");
        }
    }

    /**
     * Método para devolver um item.
     */
    private void devolverItem() {
        String idItem = JOptionPane.showInputDialog("Informe o ID do item para devolução:");
        Item item = null;
        for (Item i : usuarioAtual.itensEmprestados) {
            if (i.getId().equals(idItem)) {
                item = i;
                break;
            }
        }
        if (item != null) {
            usuarioAtual.devolverItem(item);
            JOptionPane.showMessageDialog(this, "Item devolvido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Item não encontrado nos seus empréstimos.");
        }
    }

    /**
     * Método principal para executar a interface gráfica.
     */
    public static void main(String[] args) {
        Biblioteca.getInstance().carregarDados(); // Carrega os dados ao iniciar o programa
        BibliotecaGUI gui = new BibliotecaGUI();
        gui.setVisible(true);
    }
}