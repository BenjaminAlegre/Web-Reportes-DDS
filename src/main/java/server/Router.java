package server;


import controllers.*;
import model.entities.comunidad.Comunidad;
import services.AutenticacionService;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import controllers.utils.BooleanHelper;
import controllers.utils.HandlebarsTemplateEngineBuilder;

import javax.servlet.MultipartConfigElement;
import java.util.*;


public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() throws Exception {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure() throws Exception {

        AutenticacionService autenticacionService = new AutenticacionService();

       LoginController loginController = new LoginController();
       EntidadesController entidadesController = new EntidadesController();
       IncidentesController incidentesController = new IncidentesController();
       EstablecimientosController establecimientosController = new EstablecimientosController();
       ServiciosController serviciosController = new ServiciosController();
       AuthController authController = new AuthController();
       AdministradorController administradorController = new AdministradorController();
       RankingsController rankingsController = new RankingsController();
       MiembroController miembroController = new MiembroController();
        PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController();

        Spark.path("/inicio", () -> {
            Spark.get("", incidentesController::pantallaInicio, engine);
        });

        // Login
        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
        });
        Spark.path("/callback", () -> {
            Spark.get("", authController::pantallaDeLogin, engine);
        });
        Spark.path("/logout" , () -> {
            Spark.get("", authController::logout, engine);
        });



        //Paginas Principales
        Spark.path("/paginaPrincipal", () -> {
            Spark.before("/miembro", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.before("/administrador", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("administrador");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.before("/entidad", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("entidad");
                autenticacionService.authRol(req, res, roles);
            });

            Spark.get("", pantallaPrincipalController::pantallaPaginaPrincipal, engine);
            Spark.get("/administrador", pantallaPrincipalController::pantallaPrincipalAdministrador, engine);
            Spark.get("/entidad", pantallaPrincipalController::pantallaPrincipalEntidad, engine);
            Spark.get("/miembro", pantallaPrincipalController::pantallaPrincipalMiembro, engine);
        });

        // Apertura Incidente
        Spark.path("/aperturaIncidente", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                roles.add("entidad");
                autenticacionService.authRol(req, res, roles);
            });

            Spark.get("/", incidentesController::pantallaAperturaIncidentes, engine);
            Spark.post("/registrarIncidente", incidentesController::registrarIncidente);
            Spark.post("/registrarIncidentePesado", incidentesController::registrarIncidentePesado);
        });
        //recursos asicronicos, se devuelve un strign en el formato json que son los valores que se mostraran en los desplegables
        Spark.path("/entidadesPorTipo",() ->{
            Spark.get("", entidadesController::obtenerEntidadesPorTipo);
        });
        Spark.path("/establecimientosPorEntidad",() ->{
            Spark.get("", establecimientosController::obtenerEstableciminetosDeEntidad);
        });
        Spark.path("/serviciosDeEstablecimiento",() ->{
            Spark.get("", serviciosController::obtenerServiciosDeEstablecimiento);
        });
        Spark.path("/entidadesPorPrestadora",() ->{
            Spark.get("", entidadesController::obtenerEntidadesPorPrestadora);
        });


        //Muestra incidentes activos
        Spark.path("/mostrarTodosIncidentes", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("", incidentesController::mostrarTodosIncidentes, engine);
        });
        //Muestra incidentes de las comunidades del usuario
        Spark.path("/mostrarIncidentesUsuario", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("", incidentesController::mostrarIncidentesUsuario, engine);
        });
        //Cierra el incidente
        Spark.before("/cerrarIncidente/*", (req, res) -> {
            List<String> roles = new ArrayList<>();
            roles.add("miembro");
            autenticacionService.authRol(req, res, roles);
        });
        Spark.post("/cerrarIncidente/:id", incidentesController::cerrarIncidente);

        //TODO: esto no funcina, estaba probando
        Spark.get("/prueba/buscarIncidentesPorEstado", incidentesController::pantallaBuscarIncidentesPorEstado, engine);




        //Carga masiva
        Spark.path("/cargaMasiva", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("administrador");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("/", administradorController::pantallaCargaMasiva , engine);
            Spark.post("", administradorController :: cargarDatos);
//            Spark.after("", (request, response)->{
//                System.out.println("--------------------------------------CIERRA ENTITY MANAGER Carga Masiva");
//                EntityManagerHelper.closeEntityManager();});
        });

        // Rankings
        Spark.path("/rankingsSemanales",() ->{
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("entidad");
                roles.add("administrador");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("/", rankingsController::pantallaRankings, engine);
            Spark.get("/buscar", rankingsController::mostrarRanking, engine);
            Spark.get("/buscarAsync", rankingsController::enviarRanking);
            Spark.get("/pesado", rankingsController::pantallaRankingPesado, engine);
        });

        // Administracion de tipos de usuarios y observadores
        Spark.path("/miembroLiviano", () -> {
            Spark.before("/miembro", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("/comunidades", miembroController::pantallaComunidadesDeMiembro, engine);
            Spark.post("/comunidades/:comunidadId/cambiarTipo", miembroController::cambiarTipoMiembroLiviano);
        });

        Spark.path("/miembroPesado", () -> {
            Spark.before("/miembro", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("/comunidades", miembroController::pantallaMiembroComunidadPesado, engine);
            Spark.post("/comunidades/:comunidadId/cambiarTipo", miembroController::cambiarTipoMiembroPesado);
        });



        //Buscar incidentes por estado
        Spark.path("/buscarIncidentesPorEstado",() ->{
            Spark.get("", incidentesController::pantallaBuscarIncidentesPorEstado, engine);
            Spark.get("/incidentesPorEstado", incidentesController::mostrarIncidentesPorEstado);
        });

        //Buscar incidentes por estado y comunidad (MIEMBRO)
        Spark.path("/buscarIncidentesPorEstadoYComunidad", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("", incidentesController::pantallaBuscarIncidenteComunidad, engine);
            Spark.get("/incidentesPorEstado", incidentesController::mostrarIncidentesEstadoComunidad);
        });

        Spark.before("/obtenerComunidades/*", (req, res) -> {
            List<String> roles = new ArrayList<>();
            roles.add("miembro");
            autenticacionService.authRol(req, res, roles);
        });
        Spark.get("/obtenerComunidades/:idUsuario", miembroController::obtenerComunidades);



//      Sugerencia Revision Incidente
        Spark.path("/sugerenciaRevisionIncidente", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("", incidentesController::pantallaSugerenciaRevisionIncidente, engine);
        });



    }

}
