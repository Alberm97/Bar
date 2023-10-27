package BarSynchronized;

public class Main {
    public static void main(String[] args) {
        Bar bar = new Bar(10); // Crear un bar con un aforo de 10
        for (int i = 0; i < 20; i++) {
            Thread clienteThread = new Thread(new Cliente(bar));
            clienteThread.start();
        }
    }
}