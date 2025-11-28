
package guerracartas;

public class Cola {
    private NodoCola frente;
    private NodoCola fin;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public void encolar(Carta carta) {
        NodoCola nuevoNodo = new NodoCola(carta);
        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
        tamanio++;
    }

    public Carta desencolar() {
        if (estaVacia()) return null;

        Carta carta = frente.carta;
        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }

        tamanio--;
        return carta;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public Carta verPrimera() {
        return estaVacia() ? null : frente.carta;
    }
}
