package model;

import service.ApiService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conversor {
    private ApiService ApiServicio = new ApiService();
    private Historial historial = new Historial();

    private double calcularConversion(double cantidad, double tasa) {
        return cantidad * tasa;
    }

    public void exibirMenu(){
        Scanner lectura = new Scanner(System.in);
        int opcion = 0;

        String menu = """
                ************************************************************
                Bienvenido al Conversor de Monedas
                
                1) Dólar (USD) => Soles (PEN)
                2) Soles (PEN) => Dólar (USD)
                3) Dólar (USD) => Peso Argentino (ARS)
                4) Peso Argentino (ARS) => Dólar (USD)
                5) Dólar (USD) => Real Brasileño (BRL)
                6) Real Brasileño (BRL) => Dólar (USD)
                7) Dólar (USD) => Peso Colombiano (COP)
                8) Peso Colombiano (COP) => Dólar (USD)
                9) Salir
                ************************************************************
                Elija una opción válida:
                """;

        while (opcion != 9) {
            System.out.println(menu);
            try {
                opcion = lectura.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese solo números enteros para el menú.");
                lectura.nextLine();
                continue;
            }

            if (opcion == 9) {
                System.out.println("Finalizando programa...");
                break;
            }

            String monedaBase = "";
            String monedaDestino = "";

            switch (opcion) {
                case 1 -> { monedaBase = "USD"; monedaDestino = "PEN"; }
                case 2 -> { monedaBase = "PEN"; monedaDestino = "USD"; }
                case 3 -> { monedaBase = "USD"; monedaDestino = "ARS"; }
                case 4 -> { monedaBase = "ARS";  monedaDestino = "USD"; }
                case 5 -> { monedaBase = "USD"; monedaDestino = "BRL"; }
                case 6 -> { monedaBase = "BRL"; monedaDestino = "USD"; }
                case 7 -> { monedaBase = "USD"; monedaDestino = "COP"; }
                case 8 -> { monedaBase = "COP"; monedaDestino = "USD"; }
                default -> {
                    System.out.println("Opción no válida. Intente de nuevo");
                    continue;
                }
            }

            double cantidad = 0;
            boolean cantidadValida = false;

            while (!cantidadValida) {
                System.out.println("Ingrese el valor que deseas convertir:");
                try {
                    cantidad = lectura.nextDouble();
                    if (cantidad <= 0) {
                        System.out.println(" El monto a convertir debe ser mayor a cero. Intente de nuevo.\n");
                    } else {
                        cantidadValida = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Formato de número inválido. Use números y comas/puntos decimales según su configuración regional.");
                    lectura.nextLine();
                }
            }

            try{
                double tasa = ApiServicio.obtenerTasaDeCambio(monedaBase,monedaDestino);
                double resultado = calcularConversion(cantidad, tasa);

                System.out.printf("El valor %.2f [%s] corresponde al valor final de => %.2f [%s]\n\n",
                        cantidad, monedaBase, resultado, monedaDestino);
                historial.guardarConversion(cantidad,monedaBase,resultado,monedaDestino);
            }catch(Exception e){
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
        lectura.close();
    }
}
