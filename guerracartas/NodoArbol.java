
package guerracartas;

public class NodoArbol {
    String pregunta;
    String accion;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(String contenido) {
        this.pregunta = contenido;
        this.accion = contenido;
        this.izquierdo = null;
        this.derecho = null;
    }
}



