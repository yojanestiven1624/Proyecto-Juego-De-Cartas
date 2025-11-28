
package guerracartas;


public class ListaEnlazada {
    private NodoLista cabeza;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public void agregar(Carta carta) {
        NodoLista nuevoNodo = new NodoLista(carta);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoLista actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamanio++;
    }

    public Carta eliminarPrimero() {
        if (cabeza == null) return null;

        Carta carta = cabeza.carta;
        cabeza = cabeza.siguiente;
        tamanio--;
        return carta;
    }

    public void barajar() {
        if (tamanio <= 1) return;

        Carta[] cartas = new Carta[tamanio];
        NodoLista actual = cabeza;
        int index = 0;

        while (actual != null) {
            cartas[index++] = actual.carta;
            actual = actual.siguiente;
        }

        for (int i = tamanio - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));
            Carta temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }

        cabeza = null;
        tamanio = 0;
        for (Carta carta : cartas) {
            agregar(carta);
        }
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}
