class Fundo {
    // Atributo privado que armazena o valor base do fundo
    private double valorFundo;

    // Construtor da classe Fundo
    public Fundo() {
        // Inicializa o valor do fundo com 95
        this.valorFundo = 95;
    }

    // Método que calcula o valor total do fundo com base na área fornecida
    public double calcularValorFundo(double area) {
        // Retorna o valor do fundo multiplicado pela área
        return valorFundo * area;
    }
    // Método getter para obter o valor do fundo
    public double getValorFundo() {
        return valorFundo;  // Retorna o valor da fundo armazenado
    }
}