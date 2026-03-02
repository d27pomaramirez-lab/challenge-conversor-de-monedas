package service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private static final String API_KEY = "";
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";

    public double obtenerTasaDeCambio(String monedaBase, String monedaDestino) {
        URI direccion = URI.create(URL_BASE + API_KEY + "/latest/" + monedaBase);

        //Creando cliente HTTP
        HttpClient cliente = HttpClient.newHttpClient();

        //Creando la solicitud
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            //Enviando la solicitud y recibiendo respuesta
            HttpResponse<String> response = cliente
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement elemento = JsonParser.parseString(response.body());
            JsonObject jsonObject = elemento.getAsJsonObject();
            JsonObject tasas = jsonObject.getAsJsonObject("conversion_rates");
            return tasas.get(monedaDestino).getAsDouble();

        }catch (Exception e){
            throw new RuntimeException("Error al conectar con la API: " + e.getMessage());
        }
    }
}
