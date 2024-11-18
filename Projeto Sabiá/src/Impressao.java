public class Impressao {
    // Atributos privados para armazenar os valores de cada tipo de impressão
    private double ImpressaoCanvas;
    private double ImpressaoPhotoMatte;
    private double valorImpressao;

    // Construtor da classe Impressao
    public Impressao() {
        // Inicializa os valores dos tipos de impressão
        this.ImpressaoCanvas = 550;  // Valor da impressão em Canvas
        this.ImpressaoPhotoMatte = 180;  // Valor da impressão em Photo Matte
    }

    // Método que calcula o valor da impressão em Canvas com base na área fornecida
    public double calcularImpressaoCanvas(double area) {
        return ImpressaoCanvas * area;  // Retorna o valor da impressão em Canvas multiplicado pela área
    }

    // Método que calcula o valor da impressão em Photo Matte com base na área fornecida
    public double calcularImpressaoPhotoMatte(double area) {
        return ImpressaoPhotoMatte * area;  // Retorna o valor da impressão em Photo Matte multiplicado pela área
    }

    // Método que calcula a área da impressão com base na largura e altura fornecidas
    public double calcularArea(double largura, double altura) {
        return largura * altura;  // Retorna a área, que é largura multiplicada pela altura
    }

    // Método que calcula o valor da impressão com base na escolha do tipo e na área fornecida
    public double getEscolhaImpressao(int escolhaImpressao, double area) {
        if (escolhaImpressao == 1) {
            // Calcula o valor da impressão em Canvas se a escolha for 1
            valorImpressao = calcularImpressaoCanvas(area);
            System.out.println("O valor para a Impressão Canvas é: R$ " + valorImpressao);
        } else if (escolhaImpressao == 2) {
            // Calcula o valor da impressão em Photo Matte se a escolha for 2
            valorImpressao = calcularImpressaoPhotoMatte(area);
            System.out.println("O valor para a Impressão PhotoMatte é: R$ " + valorImpressao);
        } else {
            // Imprime mensagem de erro se a escolha for inválida
            System.out.println("Escolha inválida para o tipo de Impressão.");
        }
        return valorImpressao;  // Retorna o valor da impressão calculado
    }

    // Método getter para obter o valor da impressão
    public double getValorImpressao() {
        return valorImpressao;  // Retorna o valor da impressão armazenado
    }
}
