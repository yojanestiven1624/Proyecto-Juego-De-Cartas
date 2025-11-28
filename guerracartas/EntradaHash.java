
package guerracartas;


public class EntradaHash {
    String clave;
    EstadoJugador valor;
    EntradaHash siguiente;

    public EntradaHash(String clave, EstadoJugador valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }
}
