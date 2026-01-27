package sockettcp;

public class MainCliente {

    public static void main(String[] args) {
        // crear un objeto de la clase cliente
        Cliente cliente1 = new Cliente(2200);

        // iniciar al cliente para que esté a la escucha en el puerto 2200
        cliente1.iniciar();
    }

}
