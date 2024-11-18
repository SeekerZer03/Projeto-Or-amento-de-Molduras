public class Chassi { // Atributo privado que armazena o valor base do chassi
    private double valorChassi;

    // Construtor da classe chassi
    public Chassi() {
        // Inicializa o valor do chassi
        // com 20
        this.valorChassi = 20;
    }

    // Método que calcula o valor total do Chassi com base na área fornecida
    public double calcularValorChassi(double perimetro) {
        // Retorna o valor do fundo multiplicado pela área
        return valorChassi * perimetro;
    }
    // Método getter para obter o valor do CHASSI
    public double getValorChassi() {
        return valorChassi;  // Retorna o valor da fundo armazenado
    }
}
