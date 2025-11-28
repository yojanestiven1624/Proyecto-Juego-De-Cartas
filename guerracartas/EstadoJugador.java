
package guerracartas;


public class EstadoJugador {
    String nombre;
    int cartasRestantes;
    int rondasGanadas;
    int empates;
    int guerrasGanadas;

    public EstadoJugador(String nombre) {
        this.nombre = nombre;
        this.cartasRestantes = 0;
        this.rondasGanadas = 0;
        this.empates = 0;
        this.guerrasGanadas = 0;
    }

    public String toString() {
        return String.format(
            "Jugador: %s | Cartas: %d | Rondas ganadas: %d | Empates: %d | Guerras ganadas: %d",
            nombre, cartasRestantes, rondasGanadas, empates, guerrasGanadas
        );
    }
}


