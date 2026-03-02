package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historial {
    public void guardarConversion(double cantidadOriginal, String monedaBase, double cantidadFinal, String monedaDestino) {
        try (FileWriter escritura = new FileWriter("historial.txt", true)) {
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String fechaFormateada = ahora.format(formato);
            String registro = String.format("[%s] Conversión: %.2f [%s] ===> %.2f [%s]\n",
                    fechaFormateada, cantidadOriginal, monedaBase, cantidadFinal, monedaDestino);

            escritura.write(registro);

        } catch (IOException e) {
            System.out.println("--> Ocurrió un error al intentar guardar el historial: " + e.getMessage());
        }
    }
}
