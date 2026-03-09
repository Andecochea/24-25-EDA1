import java.util.Random;

public class Fila {
    private int capacidad;
    private String[] personas;
    private int contador;

    public Fila(int capacidad) {
        this.capacidad = capacidad;
        this.personas = new String[capacidad];
        this.contador = 0;
    }

    public void abrirFila() {
        System.out.println("La fila ha sido abierta.");
    }

    public void agregarPersona(String nombre) {
        if (contador < capacidad) {
            personas[contador] = nombre;
            contador++;
            System.out.println(nombre + " ha llegado a la fila.");
        } else {
            System.out.println("La fila está llena.");
        }
    }

    public void atenderPersona() {
        if (contador > 0) {
            String personaAtendida = personas[0];
            desplazarIzquierda(0);
            System.out.println(personaAtendida + " ha sido atendida.");
        } else {
            System.out.println("No hay personas en la fila.");
        }
    }

    public void eliminarPersona(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (personas[i].equals(nombre)) {
                desplazarIzquierda(i);
                System.out.println(nombre + " se ha ido de la fila.");
                return;
            }
        }
        System.out.println("No se encuentra " + nombre + " en la fila.");
    }

    private void desplazarIzquierda(int indiceInicio) {
        for (int i = indiceInicio + 1; i < contador; i++) {
            personas[i - 1] = personas[i];
        }
        contador--;
    }

    public void notificarExcesoDeAfluencia() {
        if (contador > 10) {
            System.out.println("Pasen por esta caja en orden de fila...");
        }
    }

    public void simularAcciones() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int accion = random.nextInt(5);
            ejecutarAccionAleatoria(accion, i, random);
            notificarExcesoDeAfluencia();
        }
    }

    private void ejecutarAccionAleatoria(int accion, int id, Random random) {
        switch (accion) {
            case 0:
                agregarPersona("Persona " + id);
                break;
            case 1:
                atenderPersona();
                break;
            case 2:
                if (contador > 0) {
                    eliminarPersona(personas[random.nextInt(contador)]);
                }
                break;
            case 3:
               
                break;
            case 4:
                colarseAlInicio("Persona " + id);
                break;
        }
    }

    public void colarseAlInicio(String nombre) {
        if (contador < capacidad) {
            for (int i = contador; i > 0; i--) {
                personas[i] = personas[i - 1];
            }
            personas[0] = nombre;
            contador++;
            System.out.println(nombre + " se ha colado al inicio.");
        } else {
            System.out.println("La fila está llena.");
        }
    }

    public static void main(String[] args) {
        Fila fila = new Fila(10);
        fila.abrirFila();
        fila.simularAcciones();
    }
}
