(function(){"use strict";var n={942:function(n,e,o){var r=o(5130),t=o(6768),i=o.p+"img/logo.8ee57422.png";const a=(0,t.Lk)("img",{alt:"Vue logo",src:i},null,-1);function s(n,e,o,r,i,s){const u=(0,t.g2)("IniciarSesion");return(0,t.uX)(),(0,t.CE)(t.FK,null,[a,(0,t.bF)(u,{msg:"Bienvenido a gestor de paquetes."})],64)}const u=n=>((0,t.Qi)("data-v-3845307a"),n=n(),(0,t.jt)(),n),c={class:"login-container"},l=u((()=>(0,t.Lk)("h1",null,"Iniciar sesión",-1))),f={class:"form-group"},d=u((()=>(0,t.Lk)("label",{for:"username"},"Usuario:",-1))),p={class:"form-group"},v=u((()=>(0,t.Lk)("label",{for:"password"},"Contraseña:",-1))),g=u((()=>(0,t.Lk)("button",{type:"submit"},"Iniciar sesión",-1))),b={key:0},m={key:1,class:"error-msg"};function h(n,e,o,i,a,s){return(0,t.uX)(),(0,t.CE)("div",c,[l,(0,t.Lk)("form",{onSubmit:e[2]||(e[2]=(0,r.D$)(((...n)=>s.login&&s.login(...n)),["prevent"]))},[(0,t.Lk)("div",f,[d,(0,t.bo)((0,t.Lk)("input",{type:"text",id:"username","onUpdate:modelValue":e[0]||(e[0]=n=>a.usuario=n),required:""},null,512),[[r.Jo,a.usuario]])]),(0,t.Lk)("div",p,[v,(0,t.bo)((0,t.Lk)("input",{type:"password",id:"password","onUpdate:modelValue":e[1]||(e[1]=n=>a.contraseña=n),required:""},null,512),[[r.Jo,a.contraseña]])]),g],32),a.sesionValida?((0,t.uX)(),(0,t.CE)("p",b,"¡Bienvenido, !")):a.sesionInvalida?((0,t.uX)(),(0,t.CE)("p",m,"Credenciales inválidas. Por favor, inténtalo de nuevo.")):(0,t.Q3)("",!0)])}var y=o(8355),k={name:"IniciarSesion",data(){return{usuario:"","contraseña":"",sesionValida:!1,sesionInvalida:!1}},methods:{async login(){try{const n=await y.A.post("/login",{usuario:this.usuario,"contraseña":this.contraseña});"success"===n.data?this.sesionValida=!0:this.sesionInvalida=!0}catch(n){console.error("Error al iniciar sesión:",n),this.sesionInvalida=!0}}}},O=o(1241);const w=(0,O.A)(k,[["render",h],["__scopeId","data-v-3845307a"]]);var I=w,L={name:"App",components:{IniciarSesion:I}};const j=(0,O.A)(L,[["render",s]]);var S=j;(0,r.Ef)(S).mount("#app")}},e={};function o(r){var t=e[r];if(void 0!==t)return t.exports;var i=e[r]={exports:{}};return n[r].call(i.exports,i,i.exports,o),i.exports}o.m=n,function(){var n=[];o.O=function(e,r,t,i){if(!r){var a=1/0;for(l=0;l<n.length;l++){r=n[l][0],t=n[l][1],i=n[l][2];for(var s=!0,u=0;u<r.length;u++)(!1&i||a>=i)&&Object.keys(o.O).every((function(n){return o.O[n](r[u])}))?r.splice(u--,1):(s=!1,i<a&&(a=i));if(s){n.splice(l--,1);var c=t();void 0!==c&&(e=c)}}return e}i=i||0;for(var l=n.length;l>0&&n[l-1][2]>i;l--)n[l]=n[l-1];n[l]=[r,t,i]}}(),function(){o.n=function(n){var e=n&&n.__esModule?function(){return n["default"]}:function(){return n};return o.d(e,{a:e}),e}}(),function(){o.d=function(n,e){for(var r in e)o.o(e,r)&&!o.o(n,r)&&Object.defineProperty(n,r,{enumerable:!0,get:e[r]})}}(),function(){o.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(n){if("object"===typeof window)return window}}()}(),function(){o.o=function(n,e){return Object.prototype.hasOwnProperty.call(n,e)}}(),function(){o.r=function(n){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(n,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(n,"__esModule",{value:!0})}}(),function(){o.p="/"}(),function(){var n={524:0};o.O.j=function(e){return 0===n[e]};var e=function(e,r){var t,i,a=r[0],s=r[1],u=r[2],c=0;if(a.some((function(e){return 0!==n[e]}))){for(t in s)o.o(s,t)&&(o.m[t]=s[t]);if(u)var l=u(o)}for(e&&e(r);c<a.length;c++)i=a[c],o.o(n,i)&&n[i]&&n[i][0](),n[i]=0;return o.O(l)},r=self["webpackChunkgestor_paquetes"]=self["webpackChunkgestor_paquetes"]||[];r.forEach(e.bind(null,0)),r.push=e.bind(null,r.push.bind(r))}();var r=o.O(void 0,[504],(function(){return o(942)}));r=o.O(r)})();
//# sourceMappingURL=app.a91ad953.js.map