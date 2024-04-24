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
    //GESTIONES OPERADOR
    {path: '/operador', component: PaginaOperador},
    {path: '/cambiar-punto-control', component: CambiarPuntoControl},
    {path: '/procesar-paquete', component: ProcesarPaquete},
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
