import javax.swing.*; // Importa as classes Swing do Java para criar componentes de interface gráfica
import java.awt.*; // Importa as classes de AWT (Abstract Window Toolkit) para manipulação gráfica

// Classe PainelComFundo que estende JPanel, criando um painel com uma imagem de fundo
class PainelComFundo extends JPanel {
    // Atributo privado que armazena a imagem de fundo
    private Image imagem;

    // Construtor que recebe o caminho da imagem como argumento
    public PainelComFundo(String caminhoImagem) {
        // Carrega a imagem usando o caminho fornecido
        this.imagem = new ImageIcon(caminhoImagem).getImage();
    }

    // Sobrescreve o método paintComponent para desenhar a imagem de fundo no painel
    @Override
    protected void paintComponent(Graphics g) {
        // Chama o método da superclasse para garantir que o painel seja renderizado corretamente
        super.paintComponent(g);
        // Desenha a imagem no painel, ajustando-a ao tamanho do componente
        g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
    }
}
