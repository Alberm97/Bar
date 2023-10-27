package BarSynchronized;

public class Bar {
    private int aforo;
    private int clientesDentro;
    private boolean sePuedeEntrar;
    private boolean sePuedeSalir;

    public Bar(int aforo) {
        this.aforo = aforo;
        this.clientesDentro = 0;
        this.sePuedeEntrar = false;
        this.sePuedeSalir = true;
    }

    public synchronized void entrar() {
        while (clientesDentro >= aforo) {
            try {
                System.out.println("Esperando para entrar...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        clientesDentro++;
        System.out.println("Cliente entró al bar. Clientes dentro: " + clientesDentro);
        if (clientesDentro < aforo) {
            sePuedeEntrar = true;
            notify();
        }
    }

    public synchronized void salir() {
        clientesDentro--;
        System.out.println("Cliente salió del bar. Clientes dentro: " + clientesDentro);
        if (sePuedeEntrar) {
            sePuedeEntrar = false;
            notify();
        }
        if (clientesDentro == 0) {
            sePuedeSalir = true;
        }
    }

    public synchronized void abrirPuertaE() {
        while (!sePuedeEntrar) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Puerta de entrada abierta");
    }

    public synchronized void abrirPuertaS() {
        while (!sePuedeSalir) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Puerta de salida abierta");
    }
}


