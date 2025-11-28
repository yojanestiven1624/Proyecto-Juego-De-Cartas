
package guerracartas;


public class GuerraDeCartas {
    private Cola mazoJugador1;
    private Cola mazoJugador2;
    private Pila historial;
    private ArbolDecision arbolDecision;
    private TablaHash estadosJugadores;
    private String nombreJugador1;
    private String nombreJugador2;
    private int numeroRonda;

    public GuerraDeCartas() {
        this.mazoJugador1 = new Cola();
        this.mazoJugador2 = new Cola();
        this.historial = new Pila();
        this.arbolDecision = new ArbolDecision();
        this.estadosJugadores = new TablaHash(10);
        this.numeroRonda = 0;
    }

    public void inicializar(String nombre1, String nombre2) {
        this.nombreJugador1 = nombre1;
        this.nombreJugador2 = nombre2;

        ListaEnlazada baraja = crearBaraja();

        System.out.println("\n Barajando cartas...");
        baraja.barajar();

        System.out.println(" Repartiendo cartas...\n");
        boolean turno = true;

        while (!baraja.estaVacia()) {
            Carta carta = baraja.eliminarPrimero();
            if (turno) mazoJugador1.encolar(carta);
            else mazoJugador2.encolar(carta);
            turno = !turno;
        }

        EstadoJugador est1 = new EstadoJugador(nombre1);
        est1.cartasRestantes = mazoJugador1.getTamanio();
        estadosJugadores.insertar(nombre1, est1);

        EstadoJugador est2 = new EstadoJugador(nombre2);
        est2.cartasRestantes = mazoJugador2.getTamanio();
        estadosJugadores.insertar(nombre2, est2);
    }

    private ListaEnlazada crearBaraja() {
        ListaEnlazada baraja = new ListaEnlazada();
        String[] palos = {"Corazones", "Diamantes", "Treboles", "Picas"};
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String p : palos)
            for (String v : valores)
                baraja.agregar(new Carta(v, p));

        return baraja;
    }

    public void jugarRonda() {
        numeroRonda++;
        System.out.println("\n----- RONDA " + numeroRonda + " ------\n");

        Carta c1 = mazoJugador1.desencolar();
        Carta c2 = mazoJugador2.desencolar();

        System.out.println(nombreJugador1 + " juega: " + c1);
        System.out.println(nombreJugador2 + " juega: " + c2);

        boolean empate = c1.getValorNumerico() == c2.getValorNumerico();

        String decision = arbolDecision.evaluar(
            empate,
            mazoJugador1.getTamanio(),
            mazoJugador2.getTamanio()
        );

        switch (decision) {
            case "GUERRA": realizarGuerra(c1, c2); break;
            case "PIERDE_JUGADOR1": finalizarJuego(nombreJugador2); break;
            case "PIERDE_JUGADOR2": finalizarJuego(nombreJugador1); break;
            default: resolverRondaNormal(c1, c2);
        }

        actualizarEstados();
    }

    private void resolverRondaNormal(Carta c1, Carta c2) {
        String ganador;
        Cola mazoGanador;

        if (c1.getValorNumerico() > c2.getValorNumerico()) {
            ganador = nombreJugador1;
            mazoGanador = mazoJugador1;
            mazoGanador.encolar(c1);
            mazoGanador.encolar(c2);

            estadosJugadores.obtener(nombreJugador1).rondasGanadas++;
        } else {
            ganador = nombreJugador2;
            mazoGanador = mazoJugador2;
            mazoGanador.encolar(c2);
            mazoGanador.encolar(c1);

            estadosJugadores.obtener(nombreJugador2).rondasGanadas++;
        }

        System.out.println("Ganador: " + ganador + "\n");
        historial.apilar("Ronda " + numeroRonda + ": " + ganador);
    }

    private void realizarGuerra(Carta c1, Carta c2) {
        Cola cartas = new Cola();
        cartas.encolar(c1);
        cartas.encolar(c2);

        EstadoJugador e1 = estadosJugadores.obtener(nombreJugador1);
        EstadoJugador e2 = estadosJugadores.obtener(nombreJugador2);

        e1.empates++;
        e2.empates++;

        System.out.println("---GUERRA---");

        Carta abajo1 = mazoJugador1.desencolar();
        Carta abajo2 = mazoJugador2.desencolar();
        cartas.encolar(abajo1);
        cartas.encolar(abajo2);

        Carta arriba1 = mazoJugador1.desencolar();
        Carta arriba2 = mazoJugador2.desencolar();

        System.out.println(nombreJugador1 + " coloca: " + arriba1);
        System.out.println(nombreJugador2 + " coloca: " + arriba2);

        cartas.encolar(arriba1);
        cartas.encolar(arriba2);

        String ganador;
        Cola mazoGanador;

        if (arriba1.getValorNumerico() > arriba2.getValorNumerico()) {
            ganador = nombreJugador1;
            mazoGanador = mazoJugador1;
            e1.guerrasGanadas++;
        } else if (arriba2.getValorNumerico() > arriba1.getValorNumerico()) {
            ganador = nombreJugador2;
            mazoGanador = mazoJugador2;
            e2.guerrasGanadas++;
        } else {
            ganador = nombreJugador1;
            mazoGanador = mazoJugador1;
            System.out.println("Empate nuevamente, gana por defecto: " + ganador);
        }

        while (!cartas.estaVacia()) {
            mazoGanador.encolar(cartas.desencolar());
        }

        System.out.println("️ Ganador de la guerra: " + ganador + "\n");
        historial.apilar("Ronda " + numeroRonda + ": GUERRA ganada por " + ganador);
    }

    private void actualizarEstados() {
        estadosJugadores.obtener(nombreJugador1).cartasRestantes = mazoJugador1.getTamanio();
        estadosJugadores.obtener(nombreJugador2).cartasRestantes = mazoJugador2.getTamanio();
    }

    public void mostrarEstado() {
        System.out.println("\n---- ESTADO ACTUAL ----");
        System.out.println(estadosJugadores.obtener(nombreJugador1));
        System.out.println(estadosJugadores.obtener(nombreJugador2));
        System.out.println("-----------------\n");
    }

    public boolean juegoTerminado() {
        return mazoJugador1.estaVacia() || mazoJugador2.estaVacia();
    }

    public void finalizarJuego(String ganador) {
        System.out.println("\n------- FIN DEL JUEGO --------");
        System.out.println("GANADOR: " + ganador);
        mostrarEstado();
        historial.mostrarHistorial();
    }

    public void mostrarHistorial() {
        historial.mostrarHistorial();
    }

    public String getNombreJugador1() { return nombreJugador1; }
    public String getNombreJugador2() { return nombreJugador2; }
}
