import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMonetaria {
    public Moneda cambioMoneda(String base, String conver, float monto) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/47a6934523b7e9ef9b00a9d8/pair/"+base+"/"+conver+"/" + monto);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("No encontre esa moneda");
        }
    }
}
