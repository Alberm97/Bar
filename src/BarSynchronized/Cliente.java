package BarSynchronized;

public class Cliente implements Runnable {
    private Bar bar;

    public Cliente(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        bar.entrar(); // Simula accionar el pulsador de entrada y esperar a que se abra la puerta
        try {
            Thread.sleep(1000); // Estar en el bar por 1 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        bar.salir(); // Simula accionar el pulsador de salida y esperar a que se abra la puerta
    }

    public static void main(String[] args) {
        Bar bar = new Bar(10); // Crear un bar con un aforo de 10
        for (int i = 0; i < 20; i++) {
            Thread clienteThread = new Thread(new Cliente(bar));
            clienteThread.start();
        }
    }
}



