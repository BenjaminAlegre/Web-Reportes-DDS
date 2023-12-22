package services;

import java.util.Base64;

public class JWTService {

    public  String decodeJWT(String jwtToken) {
        try {
            // Dividir el JWT en partes (encabezado, payload, firma)
            String[] parts = jwtToken.split("\\.");


            // Decodificar Base64 URL de la carga útil (payload)
            String base64Payload = parts[1].replace('-', '+').replace('_', '/');
            String payload = new String(Base64.getDecoder().decode(base64Payload), "UTF-8");

            return payload;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public  String obtenerValor(String jsonString, String campo) {
        int indiceInicio = jsonString.indexOf("\"" + campo + "\":");

        if (indiceInicio != -1) {
            int indiceFin = jsonString.indexOf(",", indiceInicio);
            if (indiceFin == -1) {
                indiceFin = jsonString.indexOf("}", indiceInicio);
            }

            if (indiceFin != -1) {
                // Extraer el valor del campo
                String valorCampo = jsonString.substring(indiceInicio + campo.length() + 4, indiceFin);
                // Eliminar comillas si están presentes
                valorCampo = valorCampo.replace("\"", "").trim();
                return valorCampo;
            }
        }

        return null;
    }

}
