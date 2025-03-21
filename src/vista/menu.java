package vista;

import java.util.Scanner;

import Utilidades.EstrategiaPorPrioridad;
import Utilidades.fabricaEmergencias;
import controlador.sistemaGestor;
import modelo.emergencias.Emergencia;

public class menu {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        // Traer la instancia del sistema gestor (patrón Singleton)
        sistemaGestor gestor = sistemaGestor.getInstancia();

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Sistema de Gestión de Emergencias ---");
            System.out.println("1. Registrar una nueva emergencia");
            System.out.println("2. Ver el estado de los recursos");
            System.out.println("3. Ver estadísticas del sistema");
            System.out.println("4. Seleccionar estrategia de prioridad");
            System.out.println("5. Resolver emergencias");
            System.out.println("6. Mostrar emergencias pendientes");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");

            int opcion = entrada.nextInt();
            entrada.nextLine(); // Limpia el buffer

            switch (opcion) {
                case 1: // Registrar Emergencia
                    registrarEmergencia(entrada, gestor);
                    break;

                case 2: // Ver Estado de Recursos
                    gestor.mostrarEstadoRecursos();
                    break;

                case 3: // Ver Estadísticas del Sistema
                    gestor.mostrarEstadisticas();
                    break;

                case 4: // Seleccionar Estrategia de Prioridad
                    seleccionarEstrategia(entrada, gestor);
                    break;

                case 5: // Resolver Emergencias
                    gestor.resolverEmergencia();
                    break;

                case 6: // Mostrar Emergencias Pendientes
                    gestor.mostrarEmergenciasPendientes();
                    break;

                case 7: // Salir del Sistema
                    continuar = false;
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 7.");
            }
        }

        entrada.close();
    }

    // Método para registrar una nueva emergencia
    private static void registrarEmergencia(Scanner scanner, sistemaGestor gestor) {
        System.out.println("\n--- Registrar Emergencia ---");

        // Captura de datos
        System.out.println("Ingresa el tipo de emergencia (Incendio, Robo, Accidente): ");
        String tipo = scanner.nextLine();

        System.out.println("Ingresa la ubicación de la emergencia: ");
        String ubicacion = scanner.nextLine();

        System.out.println("Ingresa el nivel de gravedad (1: Baja, 2: Media, 3: Alta): ");
        int gravedad;
        while (true) {
            gravedad = scanner.nextInt();
            if (gravedad >= 1 && gravedad <= 3) {
                break;
            } else {
                System.out.println("Por favor, ingresa un nivel de gravedad válido (1, 2 o 3): ");
            }
        }
        scanner.nextLine(); // Limpia el buffer

        // Crear la emergencia usando la fábrica
        try {
            Emergencia emergencia = fabricaEmergencias.crearEmergencia(tipo, ubicacion);
            gestor.registrarEmergencia(emergencia);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método para seleccionar una estrategia de prioridad
    private static void seleccionarEstrategia(Scanner scanner, sistemaGestor gestor) {
        System.out.println("\n--- Seleccionar Estrategia de Prioridad ---");
        System.out.println("1. Priorizar por gravedad");
        System.out.println("2. Priorizar por tipo de emergencia");
        System.out.print("Elige una opción: ");
        int opcionEstrategia = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer

        if (opcionEstrategia == 1) {
            gestor.SetEstrategia(new EstrategiaPorPrioridad());
            System.out.println("Estrategia por gravedad seleccionada.");
        } else if (opcionEstrategia == 2) {
            gestor.SetEstrategia(new EstrategiaPorPrioridad());
            System.out.println("Estrategia por tipo de emergencia seleccionada.");
        } else {
            System.out.println("Opción no válida.");
        }
    }
}
