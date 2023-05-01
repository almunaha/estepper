//menu

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
  var notifDesplegable = document.querySelector('.desplegable-notificaciones');
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
