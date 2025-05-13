import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner lectura = new Scanner(System.in);
        String opcion = "";
        String base ="";
        String conver = "";


        while(true) {
            System.out.println("""
                    ***********************************************************
                    Sea bienvenido/a al conversor de moneda
                    
                    1) Dólar =>> Peso argentino
                    2) Peso argentino =>> Dólar
                    3) Dólar =>> Real brasileño
                    4) Real brasileño =>> Dólar
                    5) Dólar=>> Peso colombiano
                    6) Peso colombiano=>> Dólar
                    7) Salir
                    Elija una opcion valida:
                    ***********************************************************
                    """);

            opcion = lectura.next();
            if (opcion.equals("7")) {
                break; // salir del while
            }
            switch (opcion) {
                case "1":
                    base = "USD";
                    conver = "ARS";
                    break;
                case "2":
                    base = "ARS";
                    conver = "USD";
                    break;
                case "3":
                    base = "USD";
                    conver = "BRL";
                    break;
                case "4":
                    base = "BRL";
                    conver = "USD";
                    break;
                case "5":
                    base = "USD";
                    conver = "COP";
                    break;
                case "6":
                    base = "COP";
                    conver = "USD";
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    continue;
            }
            try {
                System.out.println("Escribe el monto en " + base + ":");
                float monto = lectura.nextFloat(); // leer monto
                lectura.nextLine(); // limpiar buffer

                ConsultaMonetaria consulta = new ConsultaMonetaria();
                Moneda moneda = consulta.cambioMoneda(base, conver, monto);

                System.out.println("El monto de " + base + " a " + conver + ":");
                System.out.println(moneda.conversion_result() + " " + conver);

                GeneradorDeArchivo generador = new GeneradorDeArchivo();
                generador.guardarJson(moneda);

            } catch (InputMismatchException e) {
                System.out.println("Monto inválido. Ingresa un número.");
                lectura.nextLine(); // limpiar el buffer para evitar bucles infinitos
            } catch (RuntimeException | IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Finalizando la aplicación.");
        lectura.close();

    }

}
