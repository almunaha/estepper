//menu + index

$(document).ready(function () {

  //grafico de sesiones completadas en inicio
  //URL actual
  var baseUrl = window.location.origin;

  let canvas = document.getElementById("graficoSesiones").getContext("2d");
  let url = baseUrl + '/index/sesiones';

  fetch(url)
    .then(response => response.json())
    .then(data => {

      var graficaSesiones = new Chart(canvas, {
        type: "pie",


        data: {
          labels: ["Sesiones completadas", "Sesiones pendientes"],

          datasets: [
            {
              data: [data.sesionesCompletas, 10 - data.sesionesCompletas],
              backgroundColor: [
                'rgba(100, 182, 205, 0.915)', // Color para sesiones completadas
                'rgba(229, 203, 108, 0.915)'    // Color para sesiones pendientes
              ]
            }
          ]
        },

      })
    })

  //progreso de inicio 
  
  var porcentajeProgreso = document.getElementById('chartPorcentajeProgreso').getAttribute('data-porcentaje-progreso');
  var options = {
    chart: {
      height: 280,
      type: "radialBar",
    },

    series: [porcentajeProgreso], //porcentaje
    colors: ["#20E647"],
    plotOptions: {
      radialBar: {
        hollow: {
          margin: 0,
          size: "70%",
          background: "#293450"
        },
        track: {
          dropShadow: {//para el bordecito de fuera la sombrita
            enabled: true,
            top: 3,
            left: 0,
            blur: 4,
            opacity: 0.20
          }
        },
        dataLabels: {
          name: {//Para el nombre de dentro de progreso
            offsetY: -10,
            color: "#fff",
            fontSize: "15px"
          },
          value: { //Para el porcentaje
            color: "#fff",
            fontSize: "35px",
            show: true
          }
        }
      }
    },
    fill: { //para el relleno
      type: "gradient",
      gradient: {
        shade: "dark",
        type: "vertical",
        gradientToColors: ["#87d4f9"], //que vaya hacia ese azulito
        stops: [0, 100]
      }
    },
    stroke: {
      lineCap: "round" //prolongación redondeada de la línea
    },
    labels: ["Progreso"] //el nombre de dentro
  };

  var chart = new ApexCharts(document.querySelector("#chart3"), options);

  chart.render();

});

function desplazar() {

  let sidebar = document.querySelector("#sidebar-container"); //barra lateral izquierda
  let header = document.querySelector("#header");

  sidebar.classList.toggle("active"); //cambiar botón a activo
  header.classList.toggle("active");

  /* // Guardar estado del botón burger en almacenamiento local
   if (sidebar.classList.contains("active")) {
     localStorage.setItem("menuState", "active");
   } 
   
   else {
     localStorage.setItem("menuState", "inactive");
   }*/
}

/*window.onload = function () {
    let sidebar = document.querySelector("#sidebar-container"); //barra lateral izquierda
    let header = document.querySelector("#header");
  
    // Obtener estado del menú del almacenamiento local
    let menuState = localStorage.getItem("menuState");
  
    // Si hay un estado guardado, aplicarlo al menú
    if (menuState === "active") {
      sidebar.classList.add("activado");
      header.classList.add("activado");
      //content.classList.add("active");
    } else if (menuState === "inactive") {
      sidebar.classList.remove("active");
      header.classList.remove("active");
      //content.classList.remove("active");
    }
  };*/

function desplegable() {
  var menuDesplegable = document.querySelector('.desplegable-perfil');
  var display = window.getComputedStyle(menuDesplegable).getPropertyValue('display');


  // Muestra u oculta el menú desplegable según su estado actual
  if (display === 'none') {
    menuDesplegable.style.display = 'flex';
  } else {
    menuDesplegable.style.display = 'none';
  }

}

function notificaciones() {
  var notifDesplegable = document.querySelector('.desplegable-notif');
  var display = window.getComputedStyle(notifDesplegable).getPropertyValue('display');

  // Muestra u oculta las notificaciones según su estado actual
  if (display === 'none') {
    notifDesplegable.style.display = 'flex';
  } else {
    notifDesplegable.style.display = 'none';
  }

}

function mostrarRecordatorio() {
  setTimeout(function () { //temporizador, al pasar un segundo salta la alerta
    Swal.fire({
      position: 'top',
      title: '<h5>¡Hace más de una semana que no te pesas!</h5>',
      showConfirmButton: true,
      showCancelButton: true,
      confirmButtonText: '<a href="/progreso" id="enlace">Registrar</a>',
      cancelButtonText: "Cerrar",
      confirmButtonColor: "#6ca4c7",

      didRender: function () {
        const registrar = document.querySelector('#enlace');

        if (registrar) {
          registrar.style.color = 'white';
        }
      },
      showClass: {
        popup: 'animate__animated animate__fadeInDown'
      },
      hideClass: {
        popup: 'animate__animated animate__fadeOutUp'
      }

    })

  }, 200);

}
