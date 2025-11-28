
package guerracartas;


public class NodoCola {
    Carta carta;
    NodoCola siguiente;

    public NodoCola(Carta carta) {
        this.carta = carta;
        this.siguiente = null;
    }
}

