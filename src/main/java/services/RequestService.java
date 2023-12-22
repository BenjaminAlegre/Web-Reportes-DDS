package services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RequestService {
    public static String makePostRequest(String url, String authCode, String clientSecret) throws Exception {
        try {
            // Crear un cliente HTTP
            HttpClient httpClient = HttpClients.createDefault();

            // Crear una solicitud POST
            HttpPost httpPost = new HttpPost(url);

            // Configurar los encabezados de la solicitud
            httpPost.setHeader("Content-Type", "application/json");

            // Agregar datos al cuerpo de la solicitud
            String jsonBody = "{ \"code\": \"" + authCode + "\"" +
                    ",\"client_secret\": \"" + clientSecret + "\"" +
                    ",\"grant_type\": \"authorization_code\"" +
                    ",\"redirect_uri\": \"http://localhost:3000\"" +
                    ",\"client_id\": \"88BuFrmFnU78LT4FnfGV1Ml4MlMfdKxC\"" +
                    "}";
            System.out.println("Json del servidor: " +jsonBody);

            StringEntity entity = new StringEntity(jsonBody);
            httpPost.setEntity(entity);

            // Enviar la solicitud y recibir la respuesta
            HttpResponse response = httpClient.execute(httpPost);

            // Leer la respuesta
            HttpEntity responseEntity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            String line;
            StringBuilder responseString = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                responseString.append(line);
            }

            // Imprimir la respuesta
            System.out.println("Respuesta del servidor: " + responseString.toString());

            // Cerrar recursos
            reader.close();

            return responseString.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
