public class Passe {
    // Atributo privado que armazena o valor do passe-partout por metro quadrado
    private double valorPasse;

    // Construtor da classe Passe
    public Passe() {
        // Inicializa o valor do passe-partout com um valor padrão de 120
        this.valorPasse = 120; // Valor padrão do passe-partout por metro quadrado
    }

    // Método que calcula o valor do passe-partout com base na área fornecida
    public double calcularValorPasse(double area) {
        // Retorna o valor do passe-partout multiplicado pela área
        return valorPasse * area;
    }
    public double getValorPasse() {
        return valorPasse;  // Retorna o valor da Passepartout armazenado
    }
}
