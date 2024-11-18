public class Vidro {
    // Atributos privados que armazenam os valores dos diferentes tipos de vidro por metro quadrado
    private double vidroIncolor;
    private double vidroAntiReflex;
    private double entreVidros;

    // Construtor da classe Vidro
    public Vidro() {
        // Inicializa os valores dos tipos de vidro
        this.vidroIncolor = 197; // Valor do vidro incolor por metro quadrado
        this.vidroAntiReflex = 293; // Valor do vidro antirreflexo por metro quadrado
        this.entreVidros = 394; // Valor do vidro "entre vidros" por metro quadrado
    }

    // Método que calcula o valor do vidro incolor com base na área fornecida
    public double calcularValorVidroIncolor(double area) {
        return vidroIncolor * area; // Retorna o valor do vidro incolor multiplicado pela área
    }

    // Método que calcula o valor do vidro antirreflexo com base na área fornecida
    public double calcularValorVidroAntireflexo(double area) {
        return vidroAntiReflex * area; // Retorna o valor do vidro antirreflexo multiplicado pela área
    }

    // Método que calcula o valor do vidro "entre vidros" com base na área fornecida
    public double calcularValorEntreVidros(double area) {
        return entreVidros * area; // Retorna o valor do vidro "entre vidros" multiplicado pela área
    }

    // Método que calcula a área do vidro com base na largura e altura fornecidas
    public double calcularArea(double largura, double altura) {
        return (largura * altura); // Retorna a área, que é largura multiplicada pela altura
    }

    // Atributo para armazenar o valor do vidro selecionado
    double valorVidro;

    // Método que calcula o valor do vidro com base na escolha do tipo e na área fornecida
    public double getEscolhaVidro(int vidroEscolha, double area) {
        if (vidroEscolha == 1) {
            // Calcula o valor do vidro incolor se a escolha for 1
            valorVidro = this.calcularValorVidroIncolor(area);
            System.out.println("O valor para o Vidro Incolor é: R$ " + valorVidro);
        } else if (vidroEscolha == 2) {
            // Calcula o valor do vidro antirreflexo se a escolha for 2
            valorVidro = this.calcularValorVidroAntireflexo(area);
            System.out.println("O valor para o Vidro Antirreflexo é: R$ " + valorVidro);
        } else if (vidroEscolha == 3) {
            // Calcula o valor do vidro "entre vidros" se a escolha for 3
            valorVidro = this.calcularValorEntreVidros(area);
            System.out.println("O valor para o Vidro Entre Vidros é: R$ " + valorVidro);
        } else {
            // Mensagem de erro se a escolha for inválida
            System.out.println("Escolha inválida para o tipo de vidro.");
        }

        return valorVidro; // Retorna o valor do vidro calculado
    }
}
