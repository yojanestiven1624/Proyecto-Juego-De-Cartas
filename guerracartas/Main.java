
package guerracartas;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("------- BIENVENIDO A GUERRA DE CARTAS --------");

        System.out.print("Nombre del Jugador 1: ");
        String j1 = scanner.nextLine();
        if (j1.isEmpty()) j1 = "Jugador 1";

        System.out.print("Nombre del Jugador 2: ");
        String j2 = scanner.nextLine();
        if (j2.isEmpty()) j2 = "CPU";

        GuerraDeCartas juego = new GuerraDeCartas();
        juego.inicializar(j1, j2);

        System.out.println("___Que comience la batalla__");

        while (!juego.juegoTerminado()) {
            System.out.println("Presione Enter para jugar ronda...");
            String comando = scanner.nextLine().trim().toLowerCase();

            if (comando.equals("salir")) break;
            if (comando.equals("historial")) { juego.mostrarHistorial(); continue; }
            if (comando.equals("estado")) { juego.mostrarEstado(); continue; }

            juego.jugarRonda();

            if (!juego.juegoTerminado()) {
                juego.mostrarEstado();
            }
        }

        
        juego.finalizarJuego(juego.getNombreJugador1());

        scanner.close();
    }
}


