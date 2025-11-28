
package guerracartas;

public class ArbolDecision {
    private NodoArbol raiz;

    public ArbolDecision() {
        construirArbol();
    }

    private void construirArbol() {
        raiz = new NodoArbol("¿Hay empate?");
        raiz.izquierdo = new NodoArbol("Continuar juego normal");

        raiz.derecho = new NodoArbol("¿Jugador tiene al menos 2 cartas?");
        raiz.derecho.izquierdo = new NodoArbol("Jugador pierde por falta de cartas");
        raiz.derecho.derecho = new NodoArbol("Iniciar guerra");
    }

    public String evaluar(boolean hayEmpate, int cartasJ1, int cartasJ2) {
        if (!hayEmpate) return "NORMAL";
        if (cartasJ1 < 2) return "PIERDE_JUGADOR1";
        if (cartasJ2 < 2) return "PIERDE_JUGADOR2";
        return "GUERRA";
    }
}

