import {createApp} from 'vue';
import App from './App.vue';
import {createRouter, createWebHistory} from 'vue-router';

import IniciarSesion from './components/IniciarSesion.vue';

//GESTIONES ADMINISTRADOR
import PaginaAdministrador from '@/components/administrador/PaginaAdministrador.vue';
import CrearCliente from "@/components/administrador/CrearCliente.vue";
import CrearOperador from "@/components/administrador/CrearOperador.vue";
import CrearPuntoControl from "@/components/administrador/CrearPuntoControl.vue";
import CrearRecepcionista from "@/components/administrador/CrearRecepcionista.vue";
import CrearRuta from "@/components/administrador/CrearRuta.vue";
import GestionarOperador from "@/components/administrador/GestionarOperador.vue";
import GestionarCliente from "@/components/administrador/GestionarCliente.vue";
import EditarCliente from "@/components/administrador/EditarCliente.vue"
import EditarOperador from "@/components/administrador/EditarOperador.vue";
import GestionarRuta from "@/components/administrador/GestionarRuta.vue";
import GestionarPuntoDeControl from "@/components/administrador/GestionarPuntoDeControl.vue";
import ReporteCliente from "@/components/administrador/reportes/ReporteCliente.vue";
import ReporteOperadores from "@/components/administrador/reportes/ReporteOperadores.vue";
import ReporteRecepcionista from "@/components/administrador/reportes/ReporteRecepcionista.vue";
import ReporteRuta from "@/components/administrador/reportes/ReporteRuta.vue";
import EditarPuntoControl from "@/components/administrador/EditarPuntoControl.vue";
import PuntosDeControlDeRutas from "@/components/administrador/PuntosDeControlDeRutas.vue";

//GESTIONES OPERADOR
import CambiarPuntoControl from "@/components/operador/CambiarPuntoControl.vue";
import ProcesarPaquete from "@/components/operador/ProcesarPaquete.vue";
//GESTIONES RECEPCIONSISTA
import LocalizarPaqute from "@/components/recepcionista/LocalizarPaquete.vue";
import PaginaRecepcionista from "@/components/recepcionista/PaginaRecepcionista.vue";
import RecibirPaquete from "@/components/recepcionista/RecibirPaquete.vue";
import RegistrarCliente from "@/components/recepcionista/RegistrarCliente.vue";
import RegistrarPaqueteEntregado from "@/components/recepcionista/RegistrarPaqueteEntregado.vue";
import PaginaOperador from "@/components/operador/PaginaOperador.vue";
import GestionarRecepcionistas from "@/components/administrador/GestionarRecepcionistas.vue";
import EditarRecepcionista from "@/components/administrador/EditarRecepcionista.vue";

const routes = [
    {path: '/', component: IniciarSesion},
    //GESTIONES ADMINISTRADOR
    {path: '/administrador', component: PaginaAdministrador},
    {path: '/crear-cliente', component: CrearCliente},
    {path: '/crear-operador', component: CrearOperador},
    {path: '/crear-punto-de-control', component: CrearPuntoControl},
    {path: '/crear-recepcionista', component: CrearRecepcionista},
    {path: '/crear-ruta', component: CrearRuta},
    {path: '/gestionar-operadores', component: GestionarOperador},
    {path: '/gestionar-clientes', component: GestionarCliente},
    {path: '/editar-cliente/:nitCliente', component: EditarCliente},
    {path: '/editar-operador/:idOperador', component: EditarOperador},
    {path: '/gestionar-recepcionistas', component: GestionarRecepcionistas},
    {path: '/editar-recepcionista/:idRecepcionista', component: EditarRecepcionista},
    {path: '/gestionar-rutas', component: GestionarRuta},
    {path: '/gestionar-puntos-de-control', component: GestionarPuntoDeControl},
    {path: '/reporte-clientes', component: ReporteCliente},
    {path: '/reporte-operadores', component: ReporteOperadores},
    {path: '/reporte-recepcionistas', component: ReporteRecepcionista},
    {path: '/reporte-de-rutas', component: ReporteRuta},
    {path: '/editar-punto-de-control/:idPuntoControl', component: EditarPuntoControl},
    {path: '/puntos-de-control-de-ruta/:idRuta', component: PuntosDeControlDeRutas},
    //GESTIONES OPERADOR
    {path: '/operador/:idOperador', component: PaginaOperador},
    {path: '/cambiar-punto-control', component: CambiarPuntoControl},
    {path: '/procesar-paquete/:idOperador', component: ProcesarPaquete},
    //GESTIONES RECEPCIONISTA
    {path: '/localizar-paquete', component: LocalizarPaqute},
    {path: '/recepcionista', component: PaginaRecepcionista},
    {path: '/recibir-paquete', component: RecibirPaquete},
    {path: '/registrar-cliente', component: RegistrarCliente},
    {path: '/registrar-paquete-entregado', component: RegistrarPaqueteEntregado}

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);

app.use(router);
app.mount('#app');
