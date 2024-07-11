package com.gerardogutierrez.conversordemonedas.principal;
import com.gerardogutierrez.conversordemonedas.conexiones.Conexion;
import com.gerardogutierrez.conversordemonedas.modelos.Divisas;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static String input() {
        Scanner input = new Scanner(System.in);
        String x = input.nextLine();
        return x;
    }

    public static void deployMenu(List<Divisas> monedas) {
        HashMap<Integer, String> menu = new HashMap<>();
        int j = 1, x = 0;

        System.out.println("¡Bienvenid@ al conversor de monedas!");
        do {
            x = 0;
            j = 1;
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.out.println("Seleccione el tipo de moneda inicial: ");

            for (int i = 0; i < monedas.size(); i++) {

                System.out.println(j + ") " + monedas.get(i).getNombre()
                        + " [" + monedas.get(i).getBase_code() + "]");
                menu.put(j, monedas.get(i).getBase_code());
                j++;
            }

            System.out.println((menu.size() + 1) + ") Salir del conversor");
            System.out.print("Elija una opción válida: ");
            String opcion = input();
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");

            for (int i = 1; i <= menu.size(); i++) {

                if (opcion.trim().equals(String.valueOf(i)))
                {
                    deploySubMenu(menu.get(i), monedas);
                    waitKey();
                    i = menu.size() + 1;

                } else if (opcion.trim().equals(String.valueOf(menu.size() + 1))) {

                    System.out.println("Salir del programa");
                    x = 1;
                    i = menu.size() + 1;

                } else if (i == menu.size()) {

                    System.out.println("Opción no válida, vuelva a intentarlo\n");
                }
            }

        } while(x == 0);
    }

    private static void deploySubMenu(String iso, List<Divisas> monedas) {
        HashMap<Integer, String> mapSubMenu = new HashMap<>();
        int j = 1, x = 0;
        do {
            x = 0;
            j = 1;

            System.out.println("Selecione el tipo de moneda que desea convertir: ");

            for (int i = 0; i < monedas.size(); i++) {

                if(!(monedas.get(i).getBase_code().equals(iso))) {

                    System.out.println(j + ") " + monedas.get(i).getNombre() + " [" + monedas.get(i).getBase_code() + "]");
                    mapSubMenu.put(j, monedas.get(i).getBase_code());
                    j++;
                }
            }

            System.out.print("Elija una opción válida: \n");
            String opcion = input();

            for (int i = 1; i <= mapSubMenu.size(); i++) {

                if (opcion.trim().equals(String.valueOf(i))) {
                    System.out.println(Conexion.response(iso,
                            mapSubMenu.get(i), enterValue(iso, mapSubMenu.get(i))));
                    break;

                } else if (i == mapSubMenu.size()) {
                    System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
                    System.out.println("Opción no válida, vuelva a intentarlo");
                    System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                    x = 1;
                }
            }

        } while(x != 0);

        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }

    private static double enterValue(String monedaBase, String monedaDestino) {

        while(true) {
            try {
                System.out.print("Digite el monto de " + monedaBase + " que desea cambiar a " + monedaDestino + ": $");
                String value = input();
                return Double.valueOf(value);
            } catch (Exception e) {
                System.out.println("\nError, el monto debe ser numérico, vuelva a intentarlo");
            }
        }
    }
    private static void waitKey() {
        System.out.println("\nPresione cualquier tecla para continuar");
        String x = input();
    }
}
