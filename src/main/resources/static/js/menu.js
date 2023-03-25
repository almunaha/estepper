function desplazar() {

    let sidebar = document.querySelector("#sidebar-container"); //barra lateral izquierda
    let header = document.querySelector("#header");
   
    sidebar.classList.toggle("active"); //cambiar botón a activo
    header.classList.toggle("active");
}
