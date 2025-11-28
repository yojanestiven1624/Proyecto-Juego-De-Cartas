
package guerracartas;


public class NodoLista {
    Carta carta;
    NodoLista siguiente;

    public NodoLista(Carta carta) {
        this.carta = carta;
        this.siguiente = null;
    }
}

