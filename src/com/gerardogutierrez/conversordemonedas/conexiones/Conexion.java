package com.gerardogutierrez.conversordemonedas.conexiones;

import com.gerardogutierrez.conversordemonedas.modelos.Divisas;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conexion {
    public static String response(String monedaBase, String monedaDestino, double value) {

        try {
            String API_KEY = "0792c3b343b967260323d55f";
            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"
                    + API_KEY + "/pair/" + monedaBase + "/" + monedaDestino + "/" + value);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            double result = new Gson()
                    .fromJson(response.body(), Divisas.class).getConversion_result();

            return "$" + value + " "
                    + monedaBase + " = $" + String.format("%.2f",result) + " " + monedaDestino;

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
