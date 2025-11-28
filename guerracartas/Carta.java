
package guerracartas;


public class Carta {
    private String valor;
    private String palo;
    private int valorNumerico;

    public Carta(String valor, String palo) {
        this.valor = valor;
        this.palo = palo;
        this.valorNumerico = calcularValorNumerico(valor);
    }

    private int calcularValorNumerico(String valor) {
        switch (valor) {
            case "A": return 14;
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            default: return Integer.parseInt(valor);
        }
    }

    public int getValorNumerico() {
        return valorNumerico;
    }

    public String toString() {
        return valor + " de " + palo;
    }
}

