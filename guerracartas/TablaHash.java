
package guerracartas;

public class TablaHash {
    private EntradaHash[] tabla;
    private int capacidad;

    public TablaHash(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new EntradaHash[capacidad];
    }

    private int hash(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (hash * 31 + clave.charAt(i)) % capacidad;
        }
        return Math.abs(hash);
    }

    public void insertar(String clave, EstadoJugador valor) {
        int indice = hash(clave);
        EntradaHash entrada = tabla[indice];

        if (entrada == null) {
            tabla[indice] = new EntradaHash(clave, valor);
            return;
        }

        while (entrada != null) {
            if (entrada.clave.equals(clave)) {
                entrada.valor = valor;
                return;
            }
            if (entrada.siguiente == null) break;
            entrada = entrada.siguiente;
        }

        entrada.siguiente = new EntradaHash(clave, valor);
    }

    public EstadoJugador obtener(String clave) {
        int indice = hash(clave);
        EntradaHash entrada = tabla[indice];

        while (entrada != null) {
            if (entrada.clave.equals(clave)) {
                return entrada.valor;
            }
            entrada = entrada.siguiente;
        }

        return null;
    }
}

