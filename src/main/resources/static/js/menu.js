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

function notificaciones(){
  var notifDesplegable = document.querySelector('.desplegable-notif');
  var display = window.getComputedStyle(notifDesplegable).getPropertyValue('display');

  // Muestra u oculta las notificaciones según su estado actual
  if (display === 'none') {
    notifDesplegable.style.display = 'flex';
  } else {
    notifDesplegable.style.display = 'none';
  }

}