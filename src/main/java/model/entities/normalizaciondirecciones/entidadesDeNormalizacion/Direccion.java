package model.entities.normalizaciondirecciones.entidadesDeNormalizacion;

public class Direccion {
    public String nomenclatura;
    public Altura altura;
    public Calle calle;

    @Override
    public String toString() {
        return nomenclatura ;
    }

    private class Altura{
        public int valor;
    }
    private class Calle{
        public String nombre;
    }
}
