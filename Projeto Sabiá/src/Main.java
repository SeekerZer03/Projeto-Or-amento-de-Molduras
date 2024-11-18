
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
// Classe principal da aplicação

public class Main {
    // Variáveis estáticas usadas na aplicação
    private static double fatorConversao; // Fator de conversão para unidades de medida (metros ou centímetros)
    private static double area; // Área do quadro
    private static double perimetro; // Perímetro do quadro
    private static double valorTotal; // Valor total do orçamento
    // Preços dos materiais - valores padrão que podem ser editados
    private static double precoVidroIncolor = 197.0;
    private static double precoVidroAntireflexo = 293.0;
    private static double precoVidroEntreVidros = 394.0;
    private static double precoImpressaoCanvas = 550.0;
    private static double precoImpressaoPhotoMatte = 180.0;
    private static double precoFundo = 95.0;
    private static double precoPasse = 120.0;
    private static double precoChassi = 20.0;
    private static final String PRECO_FILE = "precos.properties";
    //Preços dos mateiras que podem ser editados
    private static void carregarPrecos() {
        Properties propriedades = new Properties();
        try (FileInputStream input = new FileInputStream(PRECO_FILE)) {
            propriedades.load(input);
            precoVidroIncolor = Double.parseDouble(propriedades.getProperty("vidroIncolor", "197.0"));
            precoVidroAntireflexo = Double.parseDouble(propriedades.getProperty("vidroAntireflexo", "293.0"));
            precoVidroEntreVidros = Double.parseDouble(propriedades.getProperty("vidroEntreVidros", "394.0"));
            precoImpressaoCanvas = Double.parseDouble(propriedades.getProperty("impressaoCanvas", "550.0"));
            precoImpressaoPhotoMatte = Double.parseDouble(propriedades.getProperty("impressaoPhotoMatte", "180.0"));
            precoFundo = Double.parseDouble(propriedades.getProperty("fundo", "95.0"));
            precoPasse = Double.parseDouble(propriedades.getProperty("passe", "120.0"));
            precoChassi =Double.parseDouble(propriedades.getProperty("Chassi", "20.0"));
        } catch (IOException e) {

        }
    }
//carrega o proço de cada material
    private static void salvarPrecos() {
        Properties propriedades = new Properties();
        propriedades.setProperty("vidroIncolor", String.valueOf(precoVidroIncolor));
        propriedades.setProperty("vidroAntireflexo", String.valueOf(precoVidroAntireflexo));
        propriedades.setProperty("vidroEntreVidros", String.valueOf(precoVidroEntreVidros));
        propriedades.setProperty("impressaoCanvas", String.valueOf(precoImpressaoCanvas));
        propriedades.setProperty("impressaoPhotoMatte", String.valueOf(precoImpressaoPhotoMatte));
        propriedades.setProperty("fundo", String.valueOf(precoFundo));
        propriedades.setProperty("passe", String.valueOf(precoPasse));
        propriedades.setProperty("Chassi",String.valueOf(precoChassi));
        try (FileOutputStream output = new FileOutputStream(PRECO_FILE)) {
            propriedades.store(output, "Preços dos Materiais");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Referências às classes auxiliares
    private static Vidro vidro = new Vidro(); // Referência à classe Vidro
    private static Impressao Impressao = new Impressao();
    private static Fundo fundo = new Fundo();
    private static Passe passePartout = new Passe();
    private static Chassi chassi = new Chassi();
    private static DecimalFormat df = new DecimalFormat("#.00");

    // Método principal
    public static void main(String[] args) {
        carregarPrecos();
        JFrame frame = new JFrame("Sabiá Orcamento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configuração de fechamento padrão
        frame.setSize(1200, 768); // Define o tamanho da janela
        frame.setResizable(false); // Define se a janela pode ser ou não  redimensionada
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel com a imagem de fundo
        //Jframe
        PainelComFundo painel = new PainelComFundo("backkk.jpeg");
        painel.setLayout(null); // Define o layout como nulo para uso de coordenadas absolutas

        // Criação dos componentes da interface
        JLabel sabia = new JLabel("Bem-Vindo(a)");
        sabia.setBounds(530, 130, 430, 30);
        sabia.setFont(new Font("TIMES NEW ROMAN", Font.BOLD | Font.ITALIC, 20));
        sabia.setForeground(Color.WHITE); // Define a cor do texto

        JLabel wlcm = new JLabel("Sabiá Quadros");
        wlcm.setBounds(420, 75, 500, 60);
        wlcm.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 56));
        wlcm.setForeground(Color.black);

        JLabel unidadeLabel = new JLabel("Escolha a unidade de medida:");
        unidadeLabel.setBounds(430, 180, 430, 25);
        unidadeLabel.setFont(new Font("Times New Roman", Font.ITALIC, 28));
        unidadeLabel.setForeground(Color.white);

        JButton cmButton = create3DButton("Centímetros (cm)", 400, 250);
        cmButton.setBackground(new Color(0, 0, 0, 255));
        JButton mButton = create3DButton("Metros (m)", 400, 360);
        mButton.setBackground(new Color(5,1,1));

        JButton editarButton = new JButton("Editar");
        editarButton.setBounds(515, 470, 150, 40);
        editarButton.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        editarButton.setBackground(new Color(5, 1, 1, 255));
        editarButton.setForeground(Color.WHITE);
        editarButton.setBorder(BorderFactory.createRaisedBevelBorder());

        // Adiciona os componentes ao painel
        painel.add(unidadeLabel);
        painel.add(sabia);
        painel.add(wlcm);
        painel.add(cmButton);
        painel.add(mButton);
        painel.add(editarButton);

        frame.setContentPane(painel); // Define o painel como o conteúdo da janela

        Image scaledImage = new ImageIcon("imagemicon.jpeg").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        frame.setIconImage(new ImageIcon(scaledImage).getImage());

        // Listeners para os botões de unidades de medida
        cmButton.addActionListener(e -> {
            fatorConversao = 0.01; // Define o fator de conversão para centímetros
            abrirEntradaDimensoes(frame); // Abre a janela de entrada de dimensões
        });

        mButton.addActionListener(e -> {
            fatorConversao = 1.0; // Define o fator de conversão para metros
            abrirEntradaDimensoes(frame); // Abre a janela de entrada de dimensões
        });

        // Listener para o botão "Editar"
        editarButton.addActionListener(e -> abrirTelaEditarPrecos(frame));

        frame.setVisible(true); // Torna a janela visível
    }

    // Método estático que cria um botão com aparência 3D e configurações personalizadas
    private static JButton create3DButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 400, 70);
        button.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 40));
        button.setBackground(new Color(60, 59, 59, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        return button;
    }

    // Método estático que abre a tela de entrada de dimensões
    private static void abrirEntradaDimensoes(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(null);

        JLabel sabia = new JLabel("Sabiá Quadros");
        sabia.setBounds(420, 65, 500, 100);
        sabia.setFont(new Font("Times New Roman", Font.BOLD, 56));
        sabia.setForeground(Color.black);

        JLabel ola = new JLabel("Olá! Vamos começar?");
        ola.setBounds(470, 140, 430, 100);
        ola.setFont(new Font("TIMES NEW ROMAN", Font.BOLD | Font.ITALIC, 30));
        ola.setForeground(Color.WHITE);

        JLabel larguraLabel = new JLabel("Insira a largura:");
        larguraLabel.setBounds(360, 250, 300, 100);
        larguraLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 30));
        larguraLabel.setForeground(Color.BLACK);

        JTextField larguraField = new JTextField();
        larguraField.setBounds(600, 290, 270, 30);
        larguraField.setFont(new Font("Helvetica", Font.BOLD, 25));
        larguraField.setForeground(new Color(0, 0, 0));
        larguraField.setBackground(new Color(255, 255, 255));

        JLabel alturaLabel = new JLabel("Insira a altura:");
        alturaLabel.setBounds(360, 300, 260, 100);
        alturaLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 30));
        alturaLabel.setForeground(Color.black);

        JTextField alturaField = new JTextField();
        alturaField.setBounds(600, 336, 270, 30);
        alturaField.setFont(new Font("Helvetica", Font.BOLD, 25));
        alturaField.setForeground(new Color(0, 0, 0));
        alturaField.setBackground(new Color(255, 255, 255));

        JButton calcularButton = create3DButton("Próximo", 390, 450);
        calcularButton.setBackground(new Color(0, 0, 0));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(5, 2, 100, 30);
        voltarButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        voltarButton.setBackground(new Color(0, 0, 0));
        voltarButton.setForeground(Color.white);
        voltarButton.setBorder(BorderFactory.createLoweredBevelBorder());

        // Adiciona os componentes criados ao frame
        frame.add(larguraLabel);
        frame.add(larguraField);
        frame.add(alturaLabel);
        frame.add(alturaField);
        frame.add(ola);
        frame.add(sabia);
        frame.add(calcularButton);
        frame.add(voltarButton);

        // Adiciona um ActionListener ao botão "Voltar" para chamar o método abrirMenuPrincipal
        voltarButton.addActionListener(e -> abrirMenuPrincipal(frame));

        calcularButton.addActionListener(e -> {
            try {
                // Tenta converter o texto do campo de largura para um valor numérico (double)
                // Multiplica o valor inserido pela variável 'fatorConversao' (que pode ser uma constante de conversão de unidades, por exemplo)
                double largura = Double.parseDouble(larguraField.getText()) * fatorConversao;

                // Tenta converter o texto do campo de altura para um valor numérico (double)
                // Multiplica o valor inserido pela variável 'fatorConversao'
                double altura = Double.parseDouble(alturaField.getText()) * fatorConversao;

                // Calcula a área do retângulo multiplicando a largura pela altura
                area = largura * altura;
                // Calcula o perímetro do retângulo usando a fórmula 2 * (largura + altura)
                perimetro = 2 * (largura + altura);
                // Chama o método 'abrirEscolhaMateriais' para abrir uma nova tela onde o usuário escolhe os materiais
                abrirEscolhaMateriais(frame);
            } catch (NumberFormatException ex) {
                // Se ocorrer uma exceção de número inválido (por exemplo, se o usuário inserir letras em vez de números)
                // Exibe uma caixa de diálogo avisando o usuário que os "valores inseridos não são válidos"
                JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos.");
            }
        });

        frame.revalidate();
        frame.repaint();
    }

    private static void abrirEscolhaMateriais(JFrame frame) {
        frame.getContentPane().removeAll();

        // Define o layout como null para permitir posicionamento absoluto dos componentes
        frame.setLayout(null);

        // Criação do JLabel para exibir uma mensagem de aviso ao usuário
        JLabel atencao = new JLabel("ATENÇÃO!! Só clique nos materiais que desejar usar");
        atencao.setBounds(380, 130, 500, 20);
        atencao.setFont(new Font("TIMES NEW ROMAN", Font.BOLD | Font.ITALIC, 20));
        atencao.setForeground(new Color(253, 253, 253));

        // Criação do JLabel que contém o título da tela
        JLabel escolhaLabel = new JLabel("Escolha dos materiais:");
        escolhaLabel.setBounds(450, 65, 400, 56);
        escolhaLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 32));
        escolhaLabel.setForeground(Color.black);

        // Criação dos botões para cada material que o usuário pode escolher

        JButton vidroButton = create3DButton("Vidro", 180, 220);
        vidroButton.setBackground(new Color(0, 0, 0));

        JButton impressaoButton = create3DButton("Impressão", 600, 220);
        impressaoButton.setBackground(new Color(0, 0, 0));

        JButton fundoButton = create3DButton("Fundo", 180, 310);
        fundoButton.setBackground(new Color(0, 0, 0));

        JButton molduraButton = create3DButton("Moldura", 600, 310);
        molduraButton.setBackground(new Color(0, 0, 0));

        JButton passepartoutButton = create3DButton("Passepartout", 180, 400);
        passepartoutButton.setBackground(new Color(0, 0, 0));

        JButton ChassiButton = create3DButton("Chassi", 600, 400);
            ChassiButton.setBackground(new Color(0,0,0));

        JButton calcularButton = create3DButton("Calcular", 400, 490);
        calcularButton.setBackground(new Color(0, 0, 0));

        // Criação do botão "Voltar" para retornar à tela anterior
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(5, 2, 100, 30);
        voltarButton.setFont(new Font("Helvetica", Font.BOLD, 20));
        voltarButton.setBackground(new Color(0, 0, 0));
        voltarButton.setForeground(Color.white);
        voltarButton.setBorder(BorderFactory.createLoweredBevelBorder());



        // Adiciona todos os componentes ao frame
        frame.add(escolhaLabel);
        frame.add(vidroButton);
        frame.add(impressaoButton);
        frame.add(fundoButton);
        frame.add(molduraButton);
        frame.add(calcularButton);
        frame.add(atencao);
        frame.add(voltarButton);
        frame.add(passepartoutButton);
        frame.add(ChassiButton);
        // Adiciona ActionListeners para cada botão, para que ao clicar o usuário seja direcionado
        vidroButton.addActionListener(e -> escolherVidro(frame));
        impressaoButton.addActionListener(e -> escolherImpressao(frame));
        fundoButton.addActionListener(e -> calcularValorFundo(frame));
        molduraButton.addActionListener(e -> calcularValorMoldura(frame));
        passepartoutButton.addActionListener(e -> calcularValorPassepartout(frame));
        ChassiButton.addActionListener(e -> calcularValorChassi(frame));
        calcularButton.addActionListener(e -> calcularTotal(frame));

        // ActionListener para o botão "Voltar", que redefine o valor total e retorna à tela de entrada de dimensões
        voltarButton.addActionListener(e -> {
            valorTotal = 0;
            abrirEntradaDimensoes(frame);
        });

        // Atualiza o layout e repinta o frame com os novos componentes
        frame.revalidate();
        frame.repaint();
    }

    // Método para calcular o valor do fundo com base na área
    private static void calcularValorFundo(JFrame frame) {
        double valorFundo = fundo.calcularValorFundo(area);
        valorTotal += valorFundo;
        atualizarValorTotal(frame);
    }

    // Método para calcular o valor da moldura com base no perímetro
    private static void calcularValorMoldura(JFrame frame) {
        String valorMolduraStr = JOptionPane.showInputDialog(frame, "Digite o valor da Moldura por metro:");
        try {
            double valorMoldura = Double.parseDouble(valorMolduraStr);
            valorTotal += valorMoldura * perimetro;
            atualizarValorTotal(frame);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira um valor numérico válido para a moldura.");
        }
    }

    private static void  calcularValorChassi(JFrame frame) {
        double valorChassi = chassi.calcularValorChassi(perimetro);
        valorTotal += valorChassi * perimetro;
        atualizarValorTotal(frame);
    }

    // Método para calcular o valor total com um aumento de 10%
    private static void calcularTotal(JFrame frame) {
        double valorComAumento = valorTotal  * 1.10;
        JOptionPane.showMessageDialog(frame, "Valor total R$ " + df.format(valorComAumento));

        valorTotal = 0;
        area = 0;
        perimetro = 0;
        abrirEntradaDimensoes(frame);
    }
    //  cria uma interface gráfica para editar os preços de diferentes materiais, validando entradas numéricas e atualizando os valores.
    private static void abrirTelaEditarPrecos(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(null);

        JLabel editarLabel = new JLabel("Editar Preços");
        editarLabel.setBounds( 500, 70, 260, 40);
        editarLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD | Font.ITALIC, 32));
        editarLabel.setForeground(Color.black);

        JTextField vidroIncolorField = new JTextField(String.valueOf(precoVidroIncolor));
        vidroIncolorField.setBounds(600, 180, 200, 30);
        JLabel vidroIncolorLabel = new JLabel("Vidro Incolor:");
        vidroIncolorLabel.setBounds(360, 180, 200, 30);
        vidroIncolorLabel.setForeground(Color.black);
        vidroIncolorLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        JTextField vidroAntireflexoField = new JTextField(String.valueOf(precoVidroAntireflexo));
        vidroAntireflexoField.setBounds(600, 240, 200, 30);
        JLabel vidroAntireflexoLabel = new JLabel("Vidro Antireflexo:");
        vidroAntireflexoLabel.setForeground(Color.black);
        vidroAntireflexoLabel.setBounds(360, 240, 200, 30);
        vidroAntireflexoLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        JTextField vidroEntreVidrosField = new JTextField(String.valueOf(precoVidroEntreVidros));
        vidroEntreVidrosField.setBounds(600, 300, 200, 30);
        JLabel vidroEntreVidrosLabel = new JLabel("Vidro Entre Vidros:");
        vidroEntreVidrosLabel.setForeground(Color.black);
        vidroEntreVidrosLabel.setBounds(360, 300, 200, 30);
        vidroEntreVidrosLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        JTextField impressaoCanvasField = new JTextField(String.valueOf(precoImpressaoCanvas));
        impressaoCanvasField.setBounds(600, 360, 200, 30);
        JLabel impressaoCanvasLabel = new JLabel("Impressão Canvas:");
        impressaoCanvasLabel.setForeground(Color.black);
        impressaoCanvasLabel.setBounds(360, 360, 240, 30);
        impressaoCanvasLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        JTextField impressaoPhotoMatteField = new JTextField(String.valueOf(precoImpressaoPhotoMatte));
        impressaoPhotoMatteField.setBounds(600, 420, 200, 30);
        JLabel impressaoPhotoMatteLabel = new JLabel("Impressão PhotoMatte:");
        impressaoPhotoMatteLabel.setForeground(Color.black);
        impressaoPhotoMatteLabel.setBounds(360, 420, 200, 30);
        impressaoPhotoMatteLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        JTextField fundoField = new JTextField(String.valueOf(precoFundo));
        fundoField.setBounds(600, 480, 200, 30);
        JLabel fundoLabel = new JLabel("Fundo:");
        fundoLabel.setForeground(Color.black);
        fundoLabel.setBounds(360, 480, 200, 30);
        fundoLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        JTextField passeField = new JTextField(String.valueOf(precoPasse));
        passeField.setBounds(600, 540, 200, 30);
        JLabel passeLabel = new JLabel("Passe-Partout:");
        passeLabel.setForeground(Color.black);
        passeLabel.setBounds(360, 540, 200, 30);
        passeLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        JTextField ChassiField = new JTextField(String.valueOf(precoChassi));
        ChassiField.setBounds(600, 600, 200, 30);
        JLabel ChassiLabel = new JLabel("Chassi:");
        ChassiLabel.setForeground(Color.black);
        ChassiLabel.setBounds(360, 600, 200, 30);
        ChassiLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 20));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(450, 660, 100, 30);
        voltarButton.setBackground(new Color(0, 0, 0, 255));
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setBorder(BorderFactory.createRaisedBevelBorder());

        JButton prontoButton = new JButton("Pronto");
        prontoButton.setBounds(570, 660, 100, 30);
        prontoButton.setBackground(new Color(0, 0, 0, 255));
        prontoButton.setForeground(Color.WHITE);
        prontoButton.setBorder(BorderFactory.createRaisedBevelBorder());

        voltarButton.addActionListener(e -> abrirMenuPrincipal(frame));

        prontoButton.addActionListener(e -> {
            try {
                precoVidroIncolor = Double.parseDouble(vidroIncolorField.getText());
                precoVidroAntireflexo = Double.parseDouble(vidroAntireflexoField.getText());
                precoVidroEntreVidros = Double.parseDouble(vidroEntreVidrosField.getText());
                precoImpressaoCanvas = Double.parseDouble(impressaoCanvasField.getText());
                precoImpressaoPhotoMatte = Double.parseDouble(impressaoPhotoMatteField.getText());
                precoFundo = Double.parseDouble(fundoField.getText());
                precoPasse = Double.parseDouble(passeField.getText());
                precoChassi= Double.parseDouble(ChassiField.getText());

                salvarPrecos(); // Salvar os preços no arquivo
                JOptionPane.showMessageDialog(frame, "Preços atualizados com sucesso!");
                abrirMenuPrincipal(frame);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos para todos os campos.");
            }

        });

        frame.add(editarLabel);
        frame.add(vidroIncolorLabel);
        frame.add(vidroIncolorField);
        frame.add(vidroAntireflexoLabel);
        frame.add(vidroAntireflexoField);
        frame.add(vidroEntreVidrosLabel);
        frame.add(vidroEntreVidrosField);
        frame.add(impressaoCanvasLabel);
        frame.add(impressaoCanvasField);
        frame.add(impressaoPhotoMatteLabel);
        frame.add(impressaoPhotoMatteField);
        frame.add(fundoLabel);
        frame.add(fundoField);
        frame.add(passeLabel);
        frame.add(passeField);
        frame.add(ChassiLabel);
        frame.add(ChassiField);
        frame.add(voltarButton);
        frame.add(prontoButton);

        frame.revalidate();
        frame.repaint();
    }

    private static void abrirMenuPrincipal(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(null);

        JLabel sabia = new JLabel("Bem-Vindo(a)");
        sabia.setBounds(530, 130, 430, 30);
        sabia.setFont(new Font("TIMES NEW ROMAN", Font.BOLD | Font.ITALIC, 20));
        sabia.setForeground(Color.white);

        JLabel wlcm = new JLabel("Sabiá Quadros");
        wlcm.setBounds(420, 75, 500, 60);
        wlcm.setFont(new Font("Times New Roman", Font.BOLD, 56));
        wlcm.setForeground(Color.black);

        JLabel unidadeLabel = new JLabel("Escolha a unidade de medida:");
        unidadeLabel.setBounds(430, 180, 430, 25);
        unidadeLabel.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        unidadeLabel.setForeground(Color.white);

        JButton cmButton = create3DButton("Centímetros (cm)", 400, 250);
        cmButton.setBackground(new Color(0, 0, 0));

        JButton mButton = create3DButton("Metros (m)", 400, 360);
        mButton.setBackground(new Color(0, 0, 0));

        JButton editarButton = new JButton("Editar");
        editarButton.setBounds(515, 470, 150, 40);
        editarButton.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 15));
        editarButton.setBackground(new Color(0, 0, 0, 255));
        editarButton.setForeground(Color.WHITE);
        editarButton.setBorder(BorderFactory.createRaisedBevelBorder());

        frame.add(unidadeLabel);
        frame.add(sabia);
        frame.add(wlcm);
        frame.add(cmButton);
        frame.add(mButton);
        frame.add(editarButton);

        cmButton.addActionListener(e -> {
            fatorConversao = 0.01;
            abrirEntradaDimensoes(frame);
        });

        mButton.addActionListener(e -> {
            fatorConversao = 1.0;
            abrirEntradaDimensoes(frame);
        });

        editarButton.addActionListener(e -> abrirTelaEditarPrecos(frame));

        frame.revalidate();
        frame.repaint();
    }

    private static void calcularValorPassepartout(JFrame frame) {
        // Calcula o valor do Passepartout com base na área
        double valorPasse = passePartout.calcularValorPasse(area);
        valorTotal += valorPasse;
        atualizarValorTotal(frame);
    }

    private static void escolherVidro(JFrame frame) {
        // Array com as opções de tipos de vidro disponíveis para o usuário escolher
        String[] opcoesVidro = {"Vidro Incolor", "Vidro Antireflexo", "Vidro Entre Vidros"};

        // Exibe uma caixa de diálogo com as opções de vidro para o usuário selecionar
        // A caixa de diálogo retorna a opção escolhida (índice do array) ou -1 se o usuário fechar a janela
        int escolha = JOptionPane.showOptionDialog(frame, "Escolha o tipo de Vidro:", "Tipo de Vidro",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesVidro, opcoesVidro[0]);

        // Se o usuário escolheu uma opção (ou seja, escolha != -1)
        if (escolha != -1) {
            double valorVidro;
            switch (escolha) {
                case 0:
                    valorVidro = precoVidroIncolor * area;
                    break;
                case 1:
                    valorVidro = precoVidroAntireflexo * area;
                    break;
                case 2:
                    valorVidro = precoVidroEntreVidros * area;
                    break;
                default:
                    valorVidro = 0;
            }
            // Adiciona o valor do vidro escolhido ao valor total acumulado
            valorTotal += valorVidro;

            // Atualiza a exibição do valor total na interface
            atualizarValorTotal(frame);
        }
    }

    private static void escolherImpressao(JFrame frame) {
        String[] opcoesImpressao = {"Impressão Canvas", "Impressão PhotoMatte"};
        int escolha = JOptionPane.showOptionDialog(frame, "Escolha o tipo de Impressão:", "Tipo de Impressão",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesImpressao, opcoesImpressao[0]);

        if (escolha != -1) {
            double valorImpressao;
            switch (escolha) {
                case 0:
                    valorImpressao = precoImpressaoCanvas * area;
                    break;
                case 1:
                    valorImpressao = precoImpressaoPhotoMatte * area;
                    break;
                default:
                    valorImpressao = 0;
            }
            valorTotal += valorImpressao;
            atualizarValorTotal(frame);
        }
    }

    private static void atualizarValorTotal(JFrame frame) {
        // Exibe uma caixa de diálogo com o valor total acumulado até o momento, formatado como moeda
        JOptionPane.showMessageDialog(frame, "Valor total até agora: R$ " + df.format(valorTotal));
    }

    public static double getPrecoPasse() {
        return precoPasse;
    }

    public static void setPrecoPasse(double precoPasse) {
        Main.precoPasse = precoPasse;
    }

    public static Vidro getVidro() {
        return vidro;
    }

    public static void setVidro(Vidro vidro) {
        Main.vidro = vidro;
    }

    public static Impressao getImpressao() {
        return Impressao;
    }

    public static void setImpressao(Impressao impressao) {
        Impressao = impressao;
    }

    public static Chassi getChassi() {
        return chassi;
    }

    public static void setChassi(Chassi chassi) {
        Main.chassi = chassi;
    }
}

