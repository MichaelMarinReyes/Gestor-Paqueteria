package servicios;

import clases.puntosdecontrorutaydestino.*;
import clases.reportes.ReporteCliente;
import clases.roles.Cliente;
import clases.roles.Operador;
import clases.roles.Recepcionista;
import database.accionesadmin.*;
import jakarta.servlet.http.HttpServletResponse;
import util.ExcepcionApi;

import java.sql.SQLException;
import java.util.List;

public class ServicioAdministrador {
    private PuntoDeControlDao puntoDeControlDao = new PuntoDeControlDao();
    private RutaDao rutaDao = new RutaDao();
    private ClienteDao clienteDao = new ClienteDao();
    private OperadorDao operadorDao = new OperadorDao();
    private RecepcionistaDao recepcionistaDao = new RecepcionistaDao();
    private RutaPuntoControlDao rutaPuntoControlDao = new RutaPuntoControlDao();
    private ReporteClienteDao reporteClienteDao = new ReporteClienteDao();

    //CRUD PARA PUNTOS DE CONTROL
    public List<PuntoDeControl> obtenerPuntosDeControl() {
        return puntoDeControlDao.obtenerPuntosDeControl();
    }

    public PuntoDeControl crearPuntoControl(PuntoDeControl puntoDeControlEntidad) throws ExcepcionApi {
        int idPUuntoControl = puntoDeControlEntidad.getIdPuntoControl();
        if (puntoDeControlEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("No se proporcionó punto de control").build();
        }
        if (puntoDeControlDao.obtenerPuntoControl(idPUuntoControl) != null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_CONFLICT).mensaje("El punto de control ya está registrado").build();
        }
        return puntoDeControlDao.crearPuntoControl(puntoDeControlEntidad);
    }

    public void eliminarPuntoControl(int idPuntoControl) throws ExcepcionApi {
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControl(idPuntoControl);

        if (puntoDeControl.getPaquetesEnCola() > 0) {
            puntoDeControlDao.eliminarPuntoControl(idPuntoControl);
        } else {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control tiene paquetes en cola").build();
        }
    }

    public void editarPuntoControl(PuntoDeControl puntoDeControlEntidad) throws ExcepcionApi {
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControl(puntoDeControlEntidad.getIdPuntoControl());
        if (puntoDeControl != null) {
            puntoDeControl.setIdPuntoControl(puntoDeControlEntidad.getIdPuntoControl());
            puntoDeControl.setNombre(puntoDeControlEntidad.getNombre());
            puntoDeControl.setIdOperador(puntoDeControlEntidad.getIdOperador());
            puntoDeControl.setPaquetesEnCola(puntoDeControlEntidad.getPaquetesEnCola());
            puntoDeControl.setTarifaOperacion(puntoDeControlEntidad.getTarifaOperacion());
            puntoDeControl.setMaximaEnCola(puntoDeControlEntidad.getMaximaEnCola());
            puntoDeControl.setEstado(puntoDeControlEntidad.getEstado());
            puntoDeControlDao.actualizarPuntoControl(puntoDeControl);
        } else {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control no existe").build();
        }
    }

    public PuntoDeControl obtenerPuntoControl(int id) throws ExcepcionApi {
        PuntoDeControl puntoDeControl = puntoDeControlDao.obtenerPuntoControl(id);
        if (puntoDeControl == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("Punto de control no existe").build();
        }
        return puntoDeControl;
    }

    //CRUD PARA RUTAS
    public Ruta crearRuta(Ruta rutaEntidad) throws ExcepcionApi {
        if (rutaEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("No se creó la ruta\n Datos faltantes").build();
        }
        Ruta rutaExistente = rutaDao.obtenerRuta(rutaEntidad.getIdRuta());
        if (rutaExistente != null && rutaEntidad.getIdRuta() == rutaExistente.getIdRuta()) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_CONFLICT).mensaje("El ruta ya existe con ese ID").build();
        }
        return rutaDao.crearRuta(rutaEntidad);
    }

    public List<Ruta> obtenerRutas() {
        return rutaDao.obtenerRutas();
    }

    public void editarRuta(Ruta rutaEntidad, int idRuta) {
        Ruta ruta = rutaDao.obtenerRuta(rutaEntidad.getIdRuta());
        if (ruta != null) {
            ruta.setIdRuta(rutaEntidad.getIdRuta());
            ruta.setNombreRuta(rutaEntidad.getNombreRuta());
            ruta.setIdDestino(rutaEntidad.getIdDestino());
            rutaDao.actualizarRuta(ruta, idRuta);
        }
    }

    public void eliminarRuta(int idRuta) throws ExcepcionApi {
        Ruta ruta = rutaDao.obtenerRuta(idRuta);
        if (ruta == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("No se encontró la ruta").build();
        }
        rutaDao.eliminarRuta(idRuta);
    }

    //CRUD USUARIOS (CLIENTES)
    public Cliente crearCliente(Cliente clienteEntidad) throws ExcepcionApi {
        if (clienteEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("No se proporcionó cliente").build();
        }

        String nit = clienteEntidad.getNit();
        if (clienteDao.obtenerCliente(nit) != null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_CONFLICT).mensaje("El NIT ya está registrado").build();
        }
        return clienteDao.crearCliente(clienteEntidad);
    }

    public List<Cliente> obtenerClientes() {
        return clienteDao.obtenerClientes();
    }

    public void editarCliente(Cliente clienteEntidad, String nit) throws ExcepcionApi {
        try {
            Cliente cliente = clienteDao.obtenerCliente(nit);
            if (cliente != null) {
                cliente.setNit(clienteEntidad.getNit());
                cliente.setNombre(clienteEntidad.getNombre());
                cliente.setApellido(clienteEntidad.getApellido());
                cliente.setContraseña(clienteEntidad.getContraseña());
                cliente.setRol(clienteEntidad.getRol());
                cliente.setEstadoCuenta(clienteEntidad.getEstadoCuenta());
                clienteDao.actualizarCliente(cliente, nit);
            } else {
                throw new ExcepcionApi(HttpServletResponse.SC_NOT_FOUND, "Cliente no encontrado en la base de datos");
            }
        } catch (RuntimeException e) {
            throw new ExcepcionApi(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al editar el cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(String nit) {
        Cliente cliente = clienteDao.obtenerCliente(nit);
        if (cliente != null) {
            clienteDao.eliminarCliente(cliente.getNit());
        }
    }

    //CRUD PARA OPERADORES
    public List<Operador> obtenerOperadores() {
        return operadorDao.obtenerOperadores();
    }

    public Operador crearOperador(Operador operadorEntidad) throws ExcepcionApi {
        if (operadorEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("No se proporcionó operador").build();
        }

        if (operadorDao.obtenerOperador(operadorEntidad.getIdOperador()) != null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_CONFLICT).mensaje("El operador ya existe en la base de datos").build();
        }
        return operadorDao.crearOperador(operadorEntidad);
    }

    public void actualizarOperador(Operador operadorEntidad, int idOperador) {
        try {
            Operador operador = operadorDao.obtenerOperador(idOperador);
            if (operador != null) {
                operador.setIdOperador(operadorEntidad.getIdOperador());
                operador.setNombre(operadorEntidad.getNombre());
                operador.setApellido(operadorEntidad.getApellido());
                operador.setContraseña(operadorEntidad.getContraseña());
                operador.setIdPuntoControl(operadorEntidad.getIdPuntoControl());
                operador.setSesionActiva(operadorEntidad.getSesionActiva());
                operador.setRol(operadorEntidad.getRol());
                operadorDao.actualizarOperador(operador, idOperador);
            } else {
                throw new ExcepcionApi(HttpServletResponse.SC_NOT_FOUND, "Operador no encontrado en la base de datos");
            }
        } catch (RuntimeException | ExcepcionApi e) {
            try {
                throw new ExcepcionApi(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al editar el operador: " + e.getMessage());
            } catch (ExcepcionApi ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void eliminarOperador(int idOperador) throws ExcepcionApi {
        Operador operador = operadorDao.obtenerOperador(idOperador);
        if (operador == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_NOT_FOUND).mensaje("No se encontró el operador").build();
        }
        operadorDao.eliminarOperador(idOperador);
    }

    //CRUD PARA RECEPCIONISTAS
    public Recepcionista crearRecepcionista(Recepcionista recepcionistaEntidad) throws ExcepcionApi {
        if (recepcionistaEntidad == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("No se proporcionó recepcionista").build();
        }

        if (recepcionistaDao.obtenerRecepcionista(recepcionistaEntidad.getIdRecepcionista()) != null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_CONFLICT).mensaje("El recepcionista ya existe en la base de datos").build();
        }
        return recepcionistaDao.crearRecepcionista(recepcionistaEntidad);
    }

    public List<Recepcionista> obtenerRecepcionistas() {
        return recepcionistaDao.obtenerRecepcionistas();
    }

    public void actualizarRecepcionista(Recepcionista recepcionistaEntidad, int idRecepcionista) {
        try {
            Recepcionista recepcionista = recepcionistaDao.obtenerRecepcionista(idRecepcionista);
            if (recepcionista != null) {
                recepcionista.setIdRecepcionista(recepcionistaEntidad.getIdRecepcionista());
                recepcionista.setIdPaquete(recepcionistaEntidad.getIdPaquete());
                recepcionista.setNombre(recepcionistaEntidad.getNombre());
                recepcionista.setApellido(recepcionistaEntidad.getApellido());
                recepcionista.setContraseña(recepcionistaEntidad.getContraseña());
                recepcionista.setSesionActiva(recepcionistaEntidad.getSesionActiva());
                recepcionista.setRol(recepcionistaEntidad.getRol());
                recepcionistaDao.actualizarRecepcionista(recepcionista, idRecepcionista);
            } else {
                throw new ExcepcionApi(HttpServletResponse.SC_NOT_FOUND, "Recepcionista no encontrado en la base de datos");
            }
        } catch (RuntimeException | ExcepcionApi e) {
            try {
                throw new ExcepcionApi(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al editar el recepcionista: " + e.getMessage());
            } catch (ExcepcionApi ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void eliminarRecepcionista(int idRecepcionista) {
        Recepcionista recepcionista = recepcionistaDao.obtenerRecepcionista(idRecepcionista);
        if (recepcionista != null) {
            recepcionistaDao.eliminarRecepcionista(idRecepcionista);
        }
    }

    //CONTROL DE PUNTOS DE CONTROL CON RUTAS
    public List<RutaPuntoControl> obtenerPuntosDeControlDeRuta(int idRuta) {
        return rutaPuntoControlDao.obtenerPuntosControlRuta(idRuta);
    }

    public RutaPuntoControl añadirPuntoControARuta(RutaPuntoControl rutaPuntoControl) throws ExcepcionApi {
        if (rutaPuntoControl == null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_BAD_REQUEST).mensaje("No se proporcionó punto de control o ruta").build();
        }
        int idRuta = rutaPuntoControl.getIdRuta();
        int idPuntoControl = rutaPuntoControl.getIdPuntoControl();
        if (rutaPuntoControlDao.obtenerPuntoControlRuta(idRuta, idPuntoControl) != null) {
            throw ExcepcionApi.builder().code(HttpServletResponse.SC_CONFLICT).mensaje("El punto de control ya está asignado a la ruta").build();
        }
        return rutaPuntoControlDao.añadirPuntoControl(rutaPuntoControl);
    }

    //REPORTE CLIENTES
    public List<ReporteCliente> obtenerReporteClientes() {
        return reporteClienteDao.obtenerReporteClientes();
    }
}
