
package guerracartas;


public class Pila {
    private NodoPila tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    public void apilar(String evento) {
        NodoPila nuevoNodo = new NodoPila(evento);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        tamanio++;
    }

    public String desapilar() {
        if (estaVacia()) return null;

        String evento = tope.evento;
        tope = tope.siguiente;
        tamanio--;
        return evento;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public void mostrarHistorial() {
        if (estaVacia()) {
            System.out.println("No hay historial disponible.");
            return;
        }

        System.out.println("\n----- HISTORIAL DE BATALLA (últimos eventos) -----");
        NodoPila actual = tope;
        int contador = 1;

        while (actual != null && contador <= 10) {
            System.out.println(contador + ". " + actual.evento);
            actual = actual.siguiente;
            contador++;
        }
        System.out.println();
    }
}


